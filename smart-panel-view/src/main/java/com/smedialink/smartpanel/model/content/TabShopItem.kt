package com.smedialink.smartpanel.model.content

import com.smedialink.aigram.purchases.domain.model.ShopItem
import com.smedialink.smartpanel.model.TabContentItem

data class TabShopItem(
    override val contentType: TabContentItem.Type,
    val item: ShopItem
) : TabContentItem
