package com.smedialink.responses.domain.model.enums

import com.smedialink.responses.R

enum class SmartBotTag(val res: Int) {
    FREE(R.string.bot_tag_free),
    PAID(R.string.bot_tag_paid),
    MAN(R.string.bot_tag_man),
    WOMAN(R.string.bot_tag_woman),
    GREAT(R.string.bot_tag_great),
    FAMOUS(R.string.bot_tag_famous),
    HERO_MOVIE(R.string.bot_tag_hero_movie),
    HERO_CARTOON(R.string.bot_tag_hero_cartoon),
    PACK(R.string.bot_tag_pack);

    companion object {
        fun resolve(name: String): SmartBotTag? {
            return values().find { it.name == name }
        }
    }
}

