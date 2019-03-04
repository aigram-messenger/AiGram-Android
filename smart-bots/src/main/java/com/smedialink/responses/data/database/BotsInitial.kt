package com.smedialink.responses.data.database

import com.smedialink.responses.domain.model.enums.SmartBotType

object BotsInitial {

    fun getAll(): List<ShopDbModel> {
        return listOf(
            ShopDbModel(
                id = SmartBotType.ASSISTANT.name,
                smartBotType = SmartBotType.ASSISTANT,
                type = ShopDbModel.Type.INSTALLED),
            ShopDbModel(
                    id = SmartBotType.DEADPOOL.name,
                    smartBotType = SmartBotType.DEADPOOL,
                    type = ShopDbModel.Type.INSTALLED),
            ShopDbModel(
                id = SmartBotType.CHURCHILL.name,
                smartBotType = SmartBotType.CHURCHILL,
                type = ShopDbModel.Type.INSTALLED),
            ShopDbModel(
                id = SmartBotType.MONROE.name,
                smartBotType = SmartBotType.MONROE,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = SmartBotType.CONFUCIUS.name,
                smartBotType = SmartBotType.CONFUCIUS,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = SmartBotType.YODA.name,
                smartBotType = SmartBotType.YODA,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = SmartBotType.CHANEL.name,
                smartBotType = SmartBotType.CHANEL,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = SmartBotType.MEMES.name,
                smartBotType = SmartBotType.MEMES,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = SmartBotType.SOVIET_FILMS.name,
                smartBotType = SmartBotType.SOVIET_FILMS,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = SmartBotType.TRADE_BINBANK.name,
                smartBotType = SmartBotType.TRADE_BINBANK,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = SmartBotType.CELENTANO.name,
                smartBotType = SmartBotType.CELENTANO,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = SmartBotType.CORLEONE.name,
                smartBotType = SmartBotType.CORLEONE,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = SmartBotType.TERMINATOR.name,
                smartBotType = SmartBotType.TERMINATOR,
                type = ShopDbModel.Type.PAID
            ),

            ShopDbModel(
                id = SmartBotType.EINSTEIN.name,
                smartBotType = SmartBotType.EINSTEIN,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = SmartBotType.TSOY.name,
                smartBotType = SmartBotType.TSOY,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = SmartBotType.NAPOLEON.name,
                smartBotType = SmartBotType.NAPOLEON,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = SmartBotType.DAENERYS.name,
                smartBotType = SmartBotType.DAENERYS,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = SmartBotType.TERESA.name,
                smartBotType = SmartBotType.TERESA,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = SmartBotType.KARLSON.name,
                smartBotType = SmartBotType.KARLSON,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = SmartBotType.SCOOBY_DOO.name,
                smartBotType = SmartBotType.SCOOBY_DOO,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = SmartBotType.SPONGE_BOB.name,
                smartBotType = SmartBotType.SPONGE_BOB,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = SmartBotType.KUNG_FU_PANDA.name,
                smartBotType = SmartBotType.KUNG_FU_PANDA,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = SmartBotType.SHREK.name,
                smartBotType = SmartBotType.SHREK,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = SmartBotType.JON_SNOW.name,
                smartBotType = SmartBotType.JON_SNOW,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = SmartBotType.TYRION_LANNISTER.name,
                smartBotType = SmartBotType.TYRION_LANNISTER,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = SmartBotType.HOLIDAYS.name,
                smartBotType = SmartBotType.HOLIDAYS,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = SmartBotType.PUSHKIN.name,
                smartBotType = SmartBotType.PUSHKIN,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = SmartBotType.LENIN.name,
                smartBotType = SmartBotType.LENIN,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = SmartBotType.LOTR.name,
                smartBotType = SmartBotType.LOTR,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = SmartBotType.MINIONS.name,
                smartBotType = SmartBotType.MINIONS,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = SmartBotType.SIMPSONS.name,
                smartBotType = SmartBotType.SIMPSONS,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = SmartBotType.SOUTH_PARK.name,
                smartBotType = SmartBotType.SOUTH_PARK,
                type = ShopDbModel.Type.PAID
            )
        )
    }
}
