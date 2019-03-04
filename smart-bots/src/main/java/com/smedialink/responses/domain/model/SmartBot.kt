package com.smedialink.responses.domain.model

import com.smedialink.responses.domain.model.response.SmartBotResponse

interface SmartBot {
    suspend fun getResponse(lemmas: List<String>): SmartBotResponse?
}
