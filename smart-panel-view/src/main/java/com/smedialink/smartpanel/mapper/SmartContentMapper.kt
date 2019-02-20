package com.smedialink.smartpanel.mapper

import com.smedialink.responses.domain.model.NeuroBotType
import com.smedialink.responses.domain.model.SmartBotResponse
import com.smedialink.smartpanel.provider.IconsProvider
import com.smedialink.smartpanel.R
import com.smedialink.smartpanel.model.TabContent
import com.smedialink.smartpanel.model.content.AnswerItem
import com.smedialink.smartpanel.model.content.TabContentItem

class SmartContentMapper {

    /**
     * Контент для панельки
     * Магазин - всегда первая вкладка
     * Если есть рекламная - всегда вторая
     * Дальше остальные боты
     *
     * Логика отображения рекламы:
     * - в рекламной вкладке отображаются все ответы рекламного бота
     * - остальным ботам (обычным) добавляется по одному случайному ответу из рекламного
     */
    fun mapToContent(responseList: List<SmartBotResponse>?): List<TabContent> {

        val result = mutableListOf<TabContent>()

        val shop = object : TabContent {
            override val icon: Int = R.drawable.ic_bots_shop
            override val contentType: TabContent.Type = TabContent.Type.SHOP
            override val botType: NeuroBotType? = null
            override val label: Int = 0
        }

        result.add(shop)

        val adverts = mutableListOf<SmartBotResponse>()
        val normals = mutableListOf<SmartBotResponse>()

        responseList?.forEach { response ->
            if (response.type.name.contains("TRADE_"))
                adverts.add(response)
            else
                normals.add(response)
        }

        val allAdvertAnswers = mutableListOf<AnswerItem>()

        adverts.forEach { allAdvertAnswers.addAll(responseToAnswers(it, true)) }

        if (allAdvertAnswers.isNotEmpty()) {
            val advertsBot = TabContentItem(
                    icon = R.drawable.ic_adverts,
                    contentType = TabContent.Type.ADVERTISEMENT,
                    botType = null,
                    answers = allAdvertAnswers
            )

            result.add(advertsBot)
        }

        normals.forEach { normalBot ->
            val botAnswers = mutableListOf<AnswerItem>()
            adverts.forEach { advertBot ->
                botAnswers.add(getRandomAdvertAnswer(advertBot))
            }
            botAnswers.addAll(responseToAnswers(normalBot, false))

            result.add(
                    TabContentItem(
                            icon = IconsProvider.circle(normalBot.type),
                            contentType = TabContent.Type.NORMAL_BOT,
                            botType = normalBot.type,
                            label = normalBot.type.title,
                            answers = botAnswers
                    )
            )
        }

        return result
    }

    private fun getRandomAdvertAnswer(response: SmartBotResponse): AnswerItem =
            AnswerItem(
                    phrase = response.phrases.random(),
                    link = response.link,
                    isAdvertising = true
            )

    private fun responseToAnswers(response: SmartBotResponse, isAdvert: Boolean): List<AnswerItem> =
            response.phrases.map { phrase -> AnswerItem(phrase, response.link, isAdvert) }
}
