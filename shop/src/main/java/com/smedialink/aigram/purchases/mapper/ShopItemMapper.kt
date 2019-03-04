package com.smedialink.aigram.purchases.mapper

import com.smedialink.aigram.purchases.PurchaseHelper
import com.smedialink.aigram.purchases.domain.model.ShopItem
import com.smedialink.responses.data.database.ShopDbModel
import com.smedialink.responses.provider.IconsProvider

object ShopItemMapper {

    fun map(shopDbModel: ShopDbModel, productState: PurchaseHelper.Product? = null): ShopItem {
        return ShopItem(
            smartBotType = shopDbModel.smartBotType,
            avatar = IconsProvider.rounded(shopDbModel.smartBotType),
            installs = shopDbModel.installs,
            reviews = shopDbModel.ratings,
            averageRating = shopDbModel.averageRating,
            themes = shopDbModel.themes,
            phrases = shopDbModel.phrases,
            ownRating = shopDbModel.ownRating,
            state = resolveType(shopDbModel, productState)
        )
    }

    private fun resolveType(botDbModel: ShopDbModel, productState: PurchaseHelper.Product?): ShopItem.ItemState {
        //Если не null, значит это покупка todo как то упростить
        if (productState != null) {
            return if (productState.isPurchased && (
                            botDbModel.type == ShopDbModel.Type.INSTALLED || botDbModel.type == ShopDbModel.Type.PAID)) {
                ShopItem.ItemState.Installed
            } else if (productState.isPurchased && botDbModel.type == ShopDbModel.Type.ENABLED) {
                ShopItem.ItemState.Enabled
            } else {
                ShopItem.ItemState.Paid(
                        skuId = productState.sku,
                        price = productState.price
                )
            }
        }

        return if (botDbModel.type == ShopDbModel.Type.INSTALLED) {
            ShopItem.ItemState.Installed
        } else {
            ShopItem.ItemState.Enabled
        }
    }

}