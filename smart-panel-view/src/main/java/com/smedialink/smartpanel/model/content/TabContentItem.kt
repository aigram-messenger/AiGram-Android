package com.smedialink.smartpanel.model.content

import com.smedialink.responses.domain.model.NeuroBotType
import com.smedialink.smartpanel.model.TabContent

// Реализация класса, определяющего контент отдельной вкладки панели
data class TabContentItem(
        override val icon: Int,
        override val contentType: TabContent.Type,
        override val botType: NeuroBotType?,
        override val label: Int = 0,
        val answers: List<AnswerItem>
) : TabContent
