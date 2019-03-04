package com.smedialink.responses.domain.model

import com.smedialink.responses.domain.model.enums.SmartBotContentType
import com.smedialink.responses.domain.model.enums.SmartBotTag

interface SmartBotProperties {
    val label: String
    val contentType: SmartBotContentType
    val skuId: String?
    val title: Int
    val description: Int
    val wordSource: String
    val responseSource: String
    val modelAliasSource: String
    val modelPathSource: String
    val dateAdded: String
    val dateUpdated: String
    val position: Float
    val tags: List<SmartBotTag>
}

