package com.smedialink.smartpanel.model

import com.smedialink.responses.domain.model.NeuroBotType

/**
 * Контент отдельной вкладки панели ботов
 */
interface TabContent {
    val icon: Int
    val contentType: Type
    val botType: NeuroBotType?
    val label: Int

    // Виды вкладок: магазин, реклама, обычный бот
    enum class Type(val value: Int) {
        SHOP(0),
        ADVERTISEMENT(1),
        NORMAL_BOT(2)
    }
}
