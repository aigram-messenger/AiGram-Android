package com.smedialink.responses.data.database

import android.arch.persistence.room.Entity
import com.smedialink.responses.domain.model.enums.SmartBotType

@Entity(primaryKeys = ["smartBotType", "tag", "position"])
data class RecentDbModel(
    val smartBotType: SmartBotType,
    val tag: String,
    val position: Int,
    val counter: Int
)
