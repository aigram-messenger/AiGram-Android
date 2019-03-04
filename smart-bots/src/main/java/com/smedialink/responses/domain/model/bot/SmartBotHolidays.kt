package com.smedialink.responses.domain.model.bot

import com.smedialink.responses.domain.factory.ResourceFactory
import com.smedialink.responses.domain.model.SmartBot
import com.smedialink.responses.domain.model.enums.SmartBotType
import com.smedialink.responses.domain.model.response.SmartBotResponse
import java.text.SimpleDateFormat
import java.util.*

class SmartBotHolidays(factory: ResourceFactory, val type: SmartBotType) : SmartBot {

    // Список известных боту ответов, загружается из json
    private val responseSource: List<SmartBotResponse> =
        factory.getResponsesList(type)

    private val currentDateTag: String by lazy {
        SimpleDateFormat("dd.MM", Locale.getDefault()).format(Date())
    }

    override suspend fun getResponse(lemmas: List<String>): SmartBotResponse? {
        val answer = responseSource.firstOrNull { it.tag == currentDateTag }

        return if (answer != null) {
            SmartBotResponse(
                type = type,
                tag = answer.tag,
                link = "",
                phrases = answer.phrases)
        } else {
            null
        }
    }
}
