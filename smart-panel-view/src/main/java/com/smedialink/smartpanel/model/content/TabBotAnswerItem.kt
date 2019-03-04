package com.smedialink.smartpanel.model.content

import com.smedialink.responses.domain.model.enums.SmartBotType
import com.smedialink.smartpanel.model.TabContentItem

// Класс, определяющий ответ бота
data class TabBotAnswerItem(
    override val contentType: TabContentItem.Type,
    val botType: SmartBotType,
    val phrase: String,
    val tag: String,
    val link: String = ""
) : TabContentItem

