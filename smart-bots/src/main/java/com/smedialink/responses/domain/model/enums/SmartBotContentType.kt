package com.smedialink.responses.domain.model.enums

import com.smedialink.responses.R

enum class SmartBotContentType(val label: Int) {
    ADVERTS(R.string.bot_label_advert),
    NEURO(R.string.bot_label_neuro),
    NORMAL(R.string.bot_label_normal),
    RECENT(R.string.bot_label_most_used)
}
