package com.smedialink.smartpanel.mapper

import com.smedialink.responses.domain.model.enums.SmartBotHolidayType
import com.smedialink.responses.domain.model.enums.SmartBotType
import com.smedialink.responses.domain.model.response.SmartBotResponse
import com.smedialink.responses.provider.IconsProvider
import com.smedialink.smartpanel.R
import com.smedialink.smartpanel.model.SmartPanelTab
import com.smedialink.smartpanel.model.TabContentItem
import com.smedialink.smartpanel.model.content.TabBotAnswerItem
import com.smedialink.smartpanel.model.content.TabBotNameItem
import com.smedialink.smartpanel.model.tabs.BotAnswersTab
import com.smedialink.smartpanel.model.tabs.BotShopTab

class SmartContentMapper {

    fun mapToTabs(responseList: List<SmartBotResponse>?): List<SmartPanelTab> {

        val tabs = mutableListOf<SmartPanelTab>()

        val shopTab = BotShopTab()

        tabs.add(shopTab)

        val advertResponses = mutableListOf<SmartBotResponse>()
        val normalResponses = mutableListOf<SmartBotResponse>()

        responseList?.forEach { response ->
            if (response.type.name.contains("TRADE_"))
                advertResponses.add(response)
            else
                normalResponses.add(response)
        }

        // Все рекламные ответы на одной вкладке
        val allAdvertAnswers = mutableListOf<TabContentItem>()

        advertResponses.forEach { allAdvertAnswers.addAll(responseToContentItems(it)) }

        if (allAdvertAnswers.isNotEmpty()) {
            val advertsTab = BotAnswersTab(
                icon = R.drawable.ic_adverts,
                type = SmartPanelTab.Type.ADVERTISEMENT,
                botType = null,
                answers = allAdvertAnswers
            )

            tabs.add(advertsTab)
        }

        // Остальные ответы, каждый на своей вкладке
        normalResponses.forEach { response ->

            val tabAnswers = mutableListOf<TabContentItem>()

            // Имя бота вверху таба
            tabAnswers.add(
                TabBotNameItem(
                    contentType = TabContentItem.Type.NORMAL_BOT_LABEL,
                    nameResId = getContentTitle(response.type, response.tag)
                ))

            // Рекламный ответ, всем кроме ассистента
            if (response.type != SmartBotType.ASSISTANT) {
                advertResponses.forEach { advertBot -> tabAnswers.add(getRandomAdvertAnswer(advertBot)) }
            }

            // Остальные ответы
            tabAnswers.addAll(responseToContentItems(response))

            tabs.add(
                BotAnswersTab(
                    icon = getTabAvatar(response.type, response.tag),
                    type = SmartPanelTab.Type.BOT,
                    botType = response.type,
                    answers = tabAnswers
                )
            )
        }

        return tabs
    }

    private fun getRandomAdvertAnswer(response: SmartBotResponse): TabContentItem =
        TabBotAnswerItem(
            contentType = TabContentItem.Type.ADVERT_BOT_ANSWER,
            botType = response.type,
            phrase = response.phrases.random(),
            tag = response.tag,
            link = response.link
        )

    private fun responseToContentItems(response: SmartBotResponse): List<TabContentItem> =
        response.phrases.map { text ->
            TabBotAnswerItem(
                contentType =
                if (isAdvert(response.type))
                    TabContentItem.Type.ADVERT_BOT_ANSWER
                else
                    TabContentItem.Type.NORMAL_BOT_ANSWER,
                botType = response.type,
                phrase = text,
                tag = response.tag,
                link = response.link
            )
        }

    private fun isAdvert(type: SmartBotType): Boolean =
        type.name.contains("TRADE_")

    private fun getContentTitle(type: SmartBotType, tag: String): Int {
        return if (type != SmartBotType.HOLIDAYS) {
            type.title
        } else {
            SmartBotHolidayType.labelByTag(tag)
        }
    }

    private fun getTabAvatar(type: SmartBotType, tag: String): Int {
        return if (type != SmartBotType.HOLIDAYS) {
            IconsProvider.circle(type)
        } else {
            IconsProvider.circleHoliday(tag)
        }
    }
}
