package com.smedialink.smartpanel.model.content

// Класс, определяющий ответ бота
data class AnswerItem(
    val phrase: String,
    val link: String = "",
    val isAdvertising: Boolean = false
)