package com.smedialink.aigram.purchases.domain

import com.smedialink.aigram.purchases.PurchaseHelper
import com.smedialink.aigram.purchases.domain.model.ShopItem
import com.smedialink.aigram.purchases.domain.repository.ShopRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ManageShopItemUseCase(private val shopRepository: ShopRepository) {

    private val compositeDisposable = CompositeDisposable()
    private val purchaseHelper = PurchaseHelper.get()

    fun manage(shopItem: ShopItem, refreshPanel: Boolean) {
        val state = shopItem.state
        val completable = when (state) {
            ShopItem.ItemState.Enabled ->
                shopRepository.markInstalled(shopItem.smartBotType, refreshPanel)
            ShopItem.ItemState.Installed ->
                shopRepository.markEnabled(shopItem.smartBotType, refreshPanel)
            //делаем покупку и если все ок, то обновляем инвентарь.
            //Обновления придут в onInventoryLoaded
            is ShopItem.ItemState.Paid ->
                purchaseHelper.purchase(skuId = state.skuId)
                //после покупки нужно сразу устанавливать бота
                .flatMapCompletable { shopRepository.markInstalled(shopItem.smartBotType, refreshPanel) }
        }

        completable.subscribeOn(Schedulers.io())
            .subscribe({}, Throwable::printStackTrace)
            .let(compositeDisposable::add)
    }

    fun dispose() {
        compositeDisposable.clear()
    }
}