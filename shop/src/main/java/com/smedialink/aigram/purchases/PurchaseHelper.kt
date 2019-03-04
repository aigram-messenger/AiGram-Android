package com.smedialink.aigram.purchases

import android.app.Activity
import android.content.Intent
import com.smedialink.aigram.purchases.configuration.BillingProvider
import com.smedialink.responses.data.repository.SmartBotRepository
import com.smedialink.responses.domain.model.enums.SmartBotType
import com.smedialink.responses.event.SmartBotEventBus
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import org.solovyev.android.checkout.*

/**
 * Created by Oleg Sheliakin on 14.01.2019.
 * Contact me by email - olegsheliakin@gmail.com
 */

//Обертка над библиотекой Checkout
class PurchaseHelper private constructor() {

    private var uiCheckout: UiCheckout? = null
    private val cachedPurchases: MutableSet<Product> = mutableSetOf()
    private val eventBus: BehaviorSubject<SmartBotEventBus.Event> = SmartBotEventBus.get()
    private val disposable: CompositeDisposable = CompositeDisposable()
    private var repo: SmartBotRepository? = null

    val products: Set<Product>
        get() = cachedPurchases

    private var isPurchaseFlowActive = false
    private var isPurchaseHelperActive = false

    companion object {
        fun get(): PurchaseHelper {
            if (INSTANCE == null) {
                INSTANCE = PurchaseHelper()
            }

            return INSTANCE!!
        }

        private var INSTANCE: PurchaseHelper? = null
    }

    fun initWith(activity: Activity) {
        val billing = (activity.application as BillingProvider).provideBilling()
        uiCheckout = Checkout.forActivity(activity, billing)
        repo = SmartBotRepository(activity)
    }

    fun start() {
        uiCheckout?.start()
        isPurchaseHelperActive = true
    }

    fun fetchSkuDetails() {
        loadSkuDetails(SmartBotType.skus())
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        uiCheckout?.onActivityResult(requestCode, resultCode, data)
    }

    fun purchase(skuId: String): Single<Purchase> {
        if (isPurchaseFlowActive) {
            return Single.error(Exception("Purchase flow already active"))
        }
        val callback = Callback()
        uiCheckout?.apply {
            startPurchaseFlow(ProductTypes.IN_APP, skuId, null, callback)
        }

        //на успехе обновляем кэш
        return callback.single.doOnSuccess { purchase ->
            val previousProduct = cachedPurchases.find { previousPurchase ->
                previousPurchase.sku == purchase.sku
            } ?: throw IllegalStateException("Cannot find product")

            cachedPurchases.remove(previousProduct)
            cachedPurchases.add(Product(previousProduct.sku, previousProduct.price, isPurchased = true))
            purchasesRefreshed()
        }
            .doOnSubscribe { isPurchaseFlowActive = true }
            .doFinally { isPurchaseFlowActive = false }
    }

    fun onStop() {
        if (isPurchaseFlowActive) {
            isPurchaseFlowActive = false
            uiCheckout?.destroyPurchaseFlow()
        }
    }

    private fun loadSkuDetails(skus: List<String>, productType: String = ProductTypes.IN_APP) {

        if (!isPurchaseHelperActive) {
            return
        }

        val request = Inventory.Request.create()
        request.loadAllPurchases()
        request.loadSkus(productType, skus)
        uiCheckout?.loadInventory(request) { products ->
            val newProducts = mutableSetOf<Product>()
            val inAppProduct = products[productType]
            inAppProduct.skus.forEach {
                newProducts.add(Product(
                        sku = it.id.code,
                        price = it.price,
                        isPurchased = inAppProduct.isPurchased(it)
                ))
            }

            cachedPurchases.clear()
            cachedPurchases.addAll(newProducts)
            purchasesRefreshed()
        }
    }

    fun stop() {
        uiCheckout?.stop()
        disposable.clear()
        isPurchaseHelperActive = false
        repo = null
    }

    data class Product(
            val sku: String,
            val price: String,
            val isPurchased: Boolean
    )

    private fun purchasesRefreshed() {
        val purchased = cachedPurchases
                .toList()
                .filter { it.isPurchased }
                .mapNotNull { SmartBotType.resolveFromSku(it.sku) }

        eventBus.onNext(SmartBotEventBus.Event.BotsPurchasesList(purchased))

        repo?.savePurchases(purchased)
                ?.subscribeOn(Schedulers.io())
                ?.subscribe({}, { it.printStackTrace() })
                ?.also { disposable.add(it) }

    }

    private class Callback : RequestListener<Purchase> {

        private val subject: PublishSubject<Purchase> = PublishSubject.create()

        val single: Single<Purchase> = subject.firstOrError()

        override fun onSuccess(result: Purchase) {
            subject.onNext(result)
        }

        override fun onError(response: Int, e: java.lang.Exception) {
            subject.onError(e)
        }

    }
}