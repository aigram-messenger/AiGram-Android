package com.smedialink.aigram.purchases.domain

import com.smedialink.aigram.purchases.PurchaseHelper
import com.smedialink.aigram.purchases.domain.model.ShopItem
import com.smedialink.aigram.purchases.domain.repository.ShopRepository
import com.smedialink.aigram.purchases.mapper.ShopItemMapper
import com.smedialink.responses.data.database.ShopDbModel
import com.smedialink.responses.domain.model.ContentType
import com.smedialink.responses.domain.model.NeuroBotType
import com.smedialink.responses.event.SmartBotEventBus
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor

class GetShopItemsUseCase(private val shopRepository: ShopRepository) {

    private val publisher: PublishProcessor<List<ShopItem>> = PublishProcessor.create()
    private val disposables = CompositeDisposable()
    private val purchaseHelper = PurchaseHelper.get()

    fun getAll(): Flowable<List<ShopItem>> {
        //при подписке загружаем весь инвентарь из маркета
        purchaseHelper.fetchSkuDetails()

        //слушаем инвентарь
        SmartBotEventBus.get().subscribe { event ->
            when (event) {
                is SmartBotEventBus.Event.BotsPurchasesList -> {
                    shopRepository.getProducts()
                        .map { items ->
                            //если пришел пустой список, значит нет интернета и приложение впервые было установлено
                            //в будущем все покупки кэшируются на плэй маркете и доступны даже оффланй
                            //показываем только бесплатные или уже купленые
                            return@map if (purchaseHelper.products.isEmpty()) {
                                items.filterNot { it.type == ShopDbModel.Type.PAID }
                            } else {
                                //убираем рекламы
                                items.filterNot {
                                    it.smartBotType.contentType == ContentType.ADVERTS
                                }
                            }.map { shopDbModel ->
                                map(shopDbModel, shopDbModel.smartBotType)
                            }
                        }.subscribe(publisher)
                }
            }
        }.also { disposables.add(it) }

        return publisher
    }

    fun getByType(neuroBotType: NeuroBotType): Flowable<ShopItem> {
        return shopRepository.getByType(neuroBotType)
                .map { shopDbModel -> map(shopDbModel, neuroBotType) }
    }

    fun dispose() {
        disposables.dispose()
    }

    private fun map(shopDbModel: ShopDbModel, neuroBotType: NeuroBotType): ShopItem {
        return ShopItemMapper.map(shopDbModel, findProduct(purchaseHelper.products, neuroBotType))
    }

    private fun findProduct(products: Set<PurchaseHelper.Product>,
                            smartBotType: NeuroBotType): PurchaseHelper.Product? {
        return products.find { it.sku == smartBotType.skuId }
    }
}