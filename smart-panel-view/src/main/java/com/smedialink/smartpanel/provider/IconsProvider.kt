package com.smedialink.smartpanel.provider

import android.support.annotation.DrawableRes
import com.smedialink.responses.domain.model.NeuroBotType
import com.smedialink.smartpanel.R

/**
 * Провайдер иконок для ботов.
 * Временный, так как в будущем иконки будут лежать на сервере.
 */
object IconsProvider {

    /**
     * Иконки ботов, каждому типу в enum должна соответсвовать своя иконка
     */
    private val iconsCircleMap: Map<NeuroBotType, Int> = mapOf(
            NeuroBotType.TRADE_BINBANK to R.drawable.ic_adverts,
            NeuroBotType.CHANEL to R.drawable.bot_avatar_chanel,
            NeuroBotType.CHURCHILL to R.drawable.bot_avatar_churchill,
            NeuroBotType.CONFUCIUS to R.drawable.bot_avatar_confucius,
            NeuroBotType.MEMES to R.drawable.bot_avatar_meme,
            NeuroBotType.MONROE to R.drawable.bot_avatar_monroe,
            NeuroBotType.SOVIET_FILMS to R.drawable.bot_avatar_ussr,
            NeuroBotType.YODA to R.drawable.bot_avatar_yoda,
            NeuroBotType.CELENTANO to R.drawable.bot_avatar_celentano,
            NeuroBotType.CORLEONE to R.drawable.bot_avatar_corleone,
            NeuroBotType.EINSTEIN to R.drawable.bot_avatar_einstein,
            NeuroBotType.TERMINATOR to R.drawable.bot_avatar_terminator,
            NeuroBotType.TSOY to R.drawable.bot_avatar_tsoy,
            NeuroBotType.NAPOLEON to R.drawable.bot_avatar_napoleone,
            NeuroBotType.TERESA to R.drawable.bot_avatar_teresa,
            NeuroBotType.KARLSON to R.drawable.bot_avatar_karlsson,
            NeuroBotType.DAENERYS to R.drawable.bot_avatar_daenerys,
            NeuroBotType.SCOOBY_DOO to R.drawable.bot_avatar_scooby_doo,
            NeuroBotType.SPONGE_BOB to R.drawable.bot_avatar_sponge_bob,
            NeuroBotType.KUNG_FU_PANDA to R.drawable.bot_avatar_kung_fu_panda,
            NeuroBotType.SHREK to R.drawable.bot_avatar_shrek,
            NeuroBotType.JON_SNOW to R.drawable.bot_avatar_jon_snow,
            NeuroBotType.TYRION_LANNISTER to R.drawable.bot_avatar_tyrion_lannister,
            NeuroBotType.ASSISTANT to R.drawable.bot_avatar_assistant,
            NeuroBotType.DEADPOOL to R.drawable.bot_avatar_deadpool
    )

    private val iconsRoundedMap: Map<NeuroBotType, Int> = mapOf(
            NeuroBotType.TRADE_BINBANK to R.drawable.ic_adverts,
            NeuroBotType.CHANEL to R.drawable.bot_avatar_rounded_chanel,
            NeuroBotType.CHURCHILL to R.drawable.bot_avatar_rounded_churchill,
            NeuroBotType.CONFUCIUS to R.drawable.bot_avatar_rounded_confucius,
            NeuroBotType.MEMES to R.drawable.bot_avatar_rounded_memes,
            NeuroBotType.MONROE to R.drawable.bot_avatar_rouded_monroe,
            NeuroBotType.SOVIET_FILMS to R.drawable.bot_avatar_rounded_ussr,
            NeuroBotType.YODA to R.drawable.bot_avatar_rounded_yoda,
            NeuroBotType.CELENTANO to R.drawable.bot_avatar_rounded_celentano,
            NeuroBotType.CORLEONE to R.drawable.bot_avatar_rounded_corleone,
            NeuroBotType.EINSTEIN to R.drawable.bot_avatar_rounded_einstein,
            NeuroBotType.TERMINATOR to R.drawable.bot_avatar_rounded_terminator,
            NeuroBotType.TSOY to R.drawable.bot_avatar_rounded_tsoy,
            NeuroBotType.NAPOLEON to R.drawable.bot_avatar_rounded_napoleone,
            NeuroBotType.TERESA to R.drawable.bot_avatar_rounded_teresa,
            NeuroBotType.KARLSON to R.drawable.bot_avatar_rounded_karlsson,
            NeuroBotType.DAENERYS to R.drawable.bot_avatar_rounded_daenerys,
            NeuroBotType.SCOOBY_DOO to R.drawable.bot_avatar_rounded_scooby_doo,
            NeuroBotType.SPONGE_BOB to R.drawable.bot_avatar_rounded_sponge_bob,
            NeuroBotType.KUNG_FU_PANDA to R.drawable.bot_avatar_rounded_kung_fu_panda,
            NeuroBotType.SHREK to R.drawable.bot_avatar_rounded_shrek,
            NeuroBotType.JON_SNOW to R.drawable.bot_avatar_rounded_jon_snow,
            NeuroBotType.TYRION_LANNISTER to R.drawable.bot_avatar_rounded_tyrion_lannister,
            NeuroBotType.ASSISTANT to R.drawable.bot_avatar_rounded_assistant,
            NeuroBotType.DEADPOOL to R.drawable.bot_avatar_rounded_deadpool
    )

    @DrawableRes
    fun circle(smartBotType: NeuroBotType): Int {
        return iconsCircleMap[smartBotType] ?: 0
    }

    @DrawableRes
    fun rounded(smartBotType: NeuroBotType): Int {
        return iconsRoundedMap[smartBotType] ?: 0
    }

}