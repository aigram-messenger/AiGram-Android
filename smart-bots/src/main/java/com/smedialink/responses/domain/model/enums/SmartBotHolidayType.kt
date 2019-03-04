package com.smedialink.responses.domain.model.enums

import com.smedialink.responses.R

enum class SmartBotHolidayType(val tag: String, val label: Int) {
    FEB_23("23.02", R.string.bot_title_holiday_23rd_feb),
    MAR_08("08.03", R.string.bot_title_holiday_8th_mar),
    APR_01("01.04", R.string.bot_title_holiday_1st_apr),
    APR_12("12.04", R.string.bot_title_holiday_12th_apr);

    companion object {
        fun labelByTag(tag: String): Int =
            SmartBotHolidayType.values().firstOrNull { it.tag == tag }?.label ?: 0
    }
}
