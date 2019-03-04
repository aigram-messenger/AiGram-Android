package com.smedialink.responses.data.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.smedialink.responses.domain.model.enums.SmartBotType

@Entity
data class ShopDbModel(
    @PrimaryKey
    val id: String,
    val smartBotType: SmartBotType,
    val installs: Long = 0,
    val ratings: Long = 0,
    val averageRating: Float = 0f,
    val themes: Long = 0,
    val phrases: Long = 0,
    val ownRating: Int = 0,
    val installEventLogged: Int = 0,
    val type: Type) {

    enum class Type {
        ENABLED, INSTALLED, PAID;

        companion object {
            fun resolve(name: String) : ShopDbModel.Type? {
                return values().find { it.name == name }
            }
        }
    }

}
