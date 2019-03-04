package com.smedialink.responses.data.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class HolidaysDbModel(
    @PrimaryKey
    val userId: Int,
    val tags: String
)
