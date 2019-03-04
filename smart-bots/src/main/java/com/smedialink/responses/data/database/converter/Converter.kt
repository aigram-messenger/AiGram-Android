package com.smedialink.responses.data.database.converter

import android.arch.persistence.room.TypeConverter
import com.smedialink.responses.data.database.ShopDbModel
import com.smedialink.responses.domain.model.enums.SmartBotType

class Converter {

    companion object {
        @TypeConverter
        @JvmStatic
        fun toShopDbModelType(name: String): ShopDbModel.Type {
            return ShopDbModel.Type.resolve(name)
                    ?: throw IllegalArgumentException("Could not recognize status")
        }

        @TypeConverter
        @JvmStatic
        fun toString(type: SmartBotType): String {
            return type.name
        }

        @TypeConverter
        @JvmStatic
        fun toSmartBotType(name: String): SmartBotType {
            return SmartBotType.resolve(name)
                    ?: throw IllegalArgumentException("Could not recognize status")
        }

        @TypeConverter
        @JvmStatic
        fun toString(type: ShopDbModel.Type): String {
            return type.name
        }
    }
}
