package com.smedialink.smartpanel.model

import com.smedialink.responses.domain.model.enums.SmartBotType

/**
 * Контент отдельной вкладки панели ботов
 */
interface SmartPanelTab {
    val icon: Int
    val type: Type
    val botType: SmartBotType?

    enum class Type(val value: Int) {
        SHOP(1),
        ADVERTISEMENT(2),
        BOT(3)
    }
}
