package com.smedialink.smartpanel.model.content

import android.support.annotation.DrawableRes
import com.smedialink.aigram.purchases.domain.model.ShopItem
import com.smedialink.responses.domain.model.NeuroBotType

data class BotInfoModel(val botName: String,
                        val reviews: Long,
                        val themes: Long,
                        val phrases: Long,
                        val dateAdded: String,
                        val dateRefreshed: String,
                        val averageRating: Float,
                        val installs: Long,
                        val ownRating: Int,
                        val botInfoDescription: String,
                        val state: ShopItem.ItemState,
                        val type: NeuroBotType,
                        @DrawableRes val avatar: Int)