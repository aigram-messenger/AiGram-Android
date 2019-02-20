package com.smedialink.responses.domain.model

data class SmartBotResponseBlock(
    val link: String = "",
    val response: List<String> = emptyList(),
    val tag: String = ""
)
