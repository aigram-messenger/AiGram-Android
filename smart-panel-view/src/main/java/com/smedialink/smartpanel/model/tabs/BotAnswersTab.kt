package com.smedialink.smartpanel.model.tabs

import com.smedialink.responses.domain.model.enums.SmartBotType
import com.smedialink.smartpanel.model.SmartPanelTab
import com.smedialink.smartpanel.model.TabContentItem

// Реализация класса, определяющего контент отдельной вкладки панели
data class BotAnswersTab(
    override val icon: Int,
    override val type: SmartPanelTab.Type,
    override val botType: SmartBotType?,
    val answers: List<TabContentItem>
) : SmartPanelTab
