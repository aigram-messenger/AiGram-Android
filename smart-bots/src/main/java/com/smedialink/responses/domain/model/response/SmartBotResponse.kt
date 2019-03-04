package com.smedialink.responses.domain.model.response

import com.smedialink.responses.domain.model.enums.SmartBotType

data class SmartBotResponse(
    val type: SmartBotType,
    val tag: String,
    val link: String,
    val phrases: List<String>,
    var probability: Float = 0f
)
