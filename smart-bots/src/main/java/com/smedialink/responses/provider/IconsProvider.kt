package com.smedialink.responses.provider

import android.support.annotation.DrawableRes
import com.smedialink.responses.R
import com.smedialink.responses.domain.model.enums.SmartBotHolidayType
import com.smedialink.responses.domain.model.enums.SmartBotType

/**
 * Провайдер иконок для ботов.
 * Временный, так как в будущем иконки будут лежать на сервере.
 */
object IconsProvider {

    /**
     * Иконки ботов, каждому типу в enum должна соответсвовать своя иконка
     */
    private val iconsCircleMap: Map<SmartBotType, Int> = mapOf(
        SmartBotType.TRADE_BINBANK to R.drawable.ic_adverts,
        SmartBotType.CHANEL to R.drawable.bot_avatar_chanel,
        SmartBotType.CHURCHILL to R.drawable.bot_avatar_churchill,
        SmartBotType.CONFUCIUS to R.drawable.bot_avatar_confucius,
        SmartBotType.MEMES to R.drawable.bot_avatar_meme,
        SmartBotType.MONROE to R.drawable.bot_avatar_monroe,
        SmartBotType.SOVIET_FILMS to R.drawable.bot_avatar_ussr,
        SmartBotType.YODA to R.drawable.bot_avatar_yoda,
        SmartBotType.CELENTANO to R.drawable.bot_avatar_celentano,
        SmartBotType.CORLEONE to R.drawable.bot_avatar_corleone,
        SmartBotType.EINSTEIN to R.drawable.bot_avatar_einstein,
        SmartBotType.TERMINATOR to R.drawable.bot_avatar_terminator,
        SmartBotType.TSOY to R.drawable.bot_avatar_tsoy,
        SmartBotType.NAPOLEON to R.drawable.bot_avatar_napoleone,
        SmartBotType.TERESA to R.drawable.bot_avatar_teresa,
        SmartBotType.KARLSON to R.drawable.bot_avatar_karlsson,
        SmartBotType.DAENERYS to R.drawable.bot_avatar_daenerys,
        SmartBotType.SCOOBY_DOO to R.drawable.bot_avatar_scooby_doo,
        SmartBotType.SPONGE_BOB to R.drawable.bot_avatar_sponge_bob,
        SmartBotType.KUNG_FU_PANDA to R.drawable.bot_avatar_kung_fu_panda,
        SmartBotType.SHREK to R.drawable.bot_avatar_shrek,
        SmartBotType.JON_SNOW to R.drawable.bot_avatar_jon_snow,
        SmartBotType.TYRION_LANNISTER to R.drawable.bot_avatar_tyrion_lannister,
        SmartBotType.ASSISTANT to R.drawable.bot_avatar_assistant,
        SmartBotType.DEADPOOL to R.drawable.bot_avatar_deadpool,
        SmartBotType.HOLIDAYS to R.drawable.bot_avatar_any,
        SmartBotType.PUSHKIN to R.drawable.bot_avatar_pushkin,
        SmartBotType.LENIN to R.drawable.bot_avatar_lenin,
        SmartBotType.LOTR to R.drawable.bot_avatar_lotr,
        SmartBotType.MINIONS to R.drawable.bot_avatar_minion,
        SmartBotType.SIMPSONS to R.drawable.bot_avatar_simpsons,
        SmartBotType.SOUTH_PARK to R.drawable.bot_avatar_south_park,
        SmartBotType.RECENT to R.drawable.ic_bots_recent
    )

    private val iconsRoundedMap: Map<SmartBotType, Int> = mapOf(
        SmartBotType.TRADE_BINBANK to R.drawable.ic_adverts,
        SmartBotType.CHANEL to R.drawable.bot_avatar_rounded_chanel,
        SmartBotType.CHURCHILL to R.drawable.bot_avatar_rounded_churchill,
        SmartBotType.CONFUCIUS to R.drawable.bot_avatar_rounded_confucius,
        SmartBotType.MEMES to R.drawable.bot_avatar_rounded_memes,
        SmartBotType.MONROE to R.drawable.bot_avatar_rouded_monroe,
        SmartBotType.SOVIET_FILMS to R.drawable.bot_avatar_rounded_ussr,
        SmartBotType.YODA to R.drawable.bot_avatar_rounded_yoda,
        SmartBotType.CELENTANO to R.drawable.bot_avatar_rounded_celentano,
        SmartBotType.CORLEONE to R.drawable.bot_avatar_rounded_corleone,
        SmartBotType.EINSTEIN to R.drawable.bot_avatar_rounded_einstein,
        SmartBotType.TERMINATOR to R.drawable.bot_avatar_rounded_terminator,
        SmartBotType.TSOY to R.drawable.bot_avatar_rounded_tsoy,
        SmartBotType.NAPOLEON to R.drawable.bot_avatar_rounded_napoleone,
        SmartBotType.TERESA to R.drawable.bot_avatar_rounded_teresa,
        SmartBotType.KARLSON to R.drawable.bot_avatar_rounded_karlsson,
        SmartBotType.DAENERYS to R.drawable.bot_avatar_rounded_daenerys,
        SmartBotType.SCOOBY_DOO to R.drawable.bot_avatar_rounded_scooby_doo,
        SmartBotType.SPONGE_BOB to R.drawable.bot_avatar_rounded_sponge_bob,
        SmartBotType.KUNG_FU_PANDA to R.drawable.bot_avatar_rounded_kung_fu_panda,
        SmartBotType.SHREK to R.drawable.bot_avatar_rounded_shrek,
        SmartBotType.JON_SNOW to R.drawable.bot_avatar_rounded_jon_snow,
        SmartBotType.TYRION_LANNISTER to R.drawable.bot_avatar_rounded_tyrion_lannister,
        SmartBotType.ASSISTANT to R.drawable.bot_avatar_rounded_assistant,
        SmartBotType.DEADPOOL to R.drawable.bot_avatar_rounded_deadpool,
        SmartBotType.HOLIDAYS to R.drawable.bot_avatar_rounded_all_holidays,
        SmartBotType.PUSHKIN to R.drawable.bot_avatar_rounded_pushkin,
        SmartBotType.LENIN to R.drawable.bot_avatar_rounded_lenin,
        SmartBotType.LOTR to R.drawable.bot_avatar_rounded_lotr,
        SmartBotType.MINIONS to R.drawable.bot_avatar_rounded_minion,
        SmartBotType.SIMPSONS to R.drawable.bot_avatar_rounded_simpsons,
        SmartBotType.SOUTH_PARK to R.drawable.bot_avatar_rounded_south_park,
        SmartBotType.RECENT to R.drawable.ic_bots_recent
    )

    private val holidaysMap: Map<SmartBotHolidayType, Int> = mapOf(
        SmartBotHolidayType.FEB_23 to R.drawable.bot_avatar_23_02,
        SmartBotHolidayType.MAR_08 to R.drawable.bot_avatar_08_03,
        SmartBotHolidayType.APR_01 to R.drawable.bot_avatar_01_04,
        SmartBotHolidayType.APR_12 to R.drawable.bot_avatar_12_04
    )

    @DrawableRes
    fun circle(smartBotType: SmartBotType): Int {
        return iconsCircleMap[smartBotType] ?: 0
    }

    @DrawableRes
    fun circleHoliday(tag: String): Int {
        val type = SmartBotHolidayType.values().firstOrNull { it.tag == tag }
        return holidaysMap[type] ?: 0
    }

    @DrawableRes
    fun rounded(smartBotType: SmartBotType): Int {
        return iconsRoundedMap[smartBotType] ?: 0
    }
}