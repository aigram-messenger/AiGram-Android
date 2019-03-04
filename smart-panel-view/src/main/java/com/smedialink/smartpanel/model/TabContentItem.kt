package com.smedialink.smartpanel.model

interface TabContentItem {
    val contentType: Type

    enum class Type(val value: Int) {
        NORMAL_BOT_ANSWER(1),
        ADVERT_BOT_ANSWER(2),
        NORMAL_BOT_LABEL(3),
        SHOP_ITEM(4)
    }
}
