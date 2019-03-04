package com.smedialink.smartpanel.model.tabs

import com.smedialink.responses.domain.model.enums.SmartBotType
import com.smedialink.smartpanel.R
import com.smedialink.smartpanel.model.SmartPanelTab
import com.smedialink.smartpanel.model.TabContentItem

data class BotShopTab(
    override val icon: Int = R.drawable.ic_bots_shop,
    override val type: SmartPanelTab.Type = SmartPanelTab.Type.SHOP,
    override val botType: SmartBotType? = null,
    val answers: List<TabContentItem> = emptyList()
) : SmartPanelTab
