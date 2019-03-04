package com.smedialink.aigram.purchases.domain.model

import com.smedialink.responses.domain.model.enums.SmartBotType

// Класс, определяющий отдельный пункт на странице магазина
data class ShopItem(
    val smartBotType: SmartBotType,
    val avatar: Int,
    val installs: Long,
    val reviews: Long,
    val averageRating: Float,
    val themes: Long,
    val phrases: Long,
    val ownRating: Int,
    val state: ItemState
) {

    sealed class ItemState {
        object Enabled : ItemState()
        object Installed : ItemState()
        data class Paid(
                val price: String,
                val skuId: String
        ) : ItemState()
    }

}
