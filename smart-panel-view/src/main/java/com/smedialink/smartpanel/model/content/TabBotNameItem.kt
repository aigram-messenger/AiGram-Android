package com.smedialink.smartpanel.model.content

import com.smedialink.smartpanel.model.TabContentItem

data class TabBotNameItem(
    override val contentType: TabContentItem.Type,
    val nameResId: Int
) : TabContentItem
