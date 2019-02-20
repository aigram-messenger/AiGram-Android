package com.smedialink.responses.domain.model

data class SmartBotResponse(
        val type: NeuroBotType,
        val tag: String,
        val link: String,
        val phrases: List<String>,
        var probability: Float = 0f
)
