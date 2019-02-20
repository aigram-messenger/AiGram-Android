package com.smedialink.responses.data.database

import com.smedialink.responses.domain.model.NeuroBotType

object BotsInitial {

    fun getAll(): List<ShopDbModel> {
        return listOf(
            ShopDbModel(
                id = NeuroBotType.ASSISTANT.name,
                smartBotType = NeuroBotType.ASSISTANT,
                type = ShopDbModel.Type.INSTALLED),
            ShopDbModel(
                    id = NeuroBotType.DEADPOOL.name,
                    smartBotType = NeuroBotType.DEADPOOL,
                    type = ShopDbModel.Type.INSTALLED),
            ShopDbModel(
                id = NeuroBotType.CHURCHILL.name,
                smartBotType = NeuroBotType.CHURCHILL,
                type = ShopDbModel.Type.INSTALLED),
            ShopDbModel(
                id = NeuroBotType.MONROE.name,
                smartBotType = NeuroBotType.MONROE,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = NeuroBotType.CONFUCIUS.name,
                smartBotType = NeuroBotType.CONFUCIUS,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = NeuroBotType.YODA.name,
                smartBotType = NeuroBotType.YODA,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = NeuroBotType.CHANEL.name,
                smartBotType = NeuroBotType.CHANEL,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = NeuroBotType.MEMES.name,
                smartBotType = NeuroBotType.MEMES,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = NeuroBotType.SOVIET_FILMS.name,
                smartBotType = NeuroBotType.SOVIET_FILMS,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = NeuroBotType.TRADE_BINBANK.name,
                smartBotType = NeuroBotType.TRADE_BINBANK,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = NeuroBotType.CELENTANO.name,
                smartBotType = NeuroBotType.CELENTANO,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = NeuroBotType.CORLEONE.name,
                smartBotType = NeuroBotType.CORLEONE,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = NeuroBotType.TERMINATOR.name,
                smartBotType = NeuroBotType.TERMINATOR,
                type = ShopDbModel.Type.PAID
            ),

            ShopDbModel(
                id = NeuroBotType.EINSTEIN.name,
                smartBotType = NeuroBotType.EINSTEIN,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = NeuroBotType.TSOY.name,
                smartBotType = NeuroBotType.TSOY,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = NeuroBotType.NAPOLEON.name,
                smartBotType = NeuroBotType.NAPOLEON,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = NeuroBotType.DAENERYS.name,
                smartBotType = NeuroBotType.DAENERYS,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = NeuroBotType.TERESA.name,
                smartBotType = NeuroBotType.TERESA,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = NeuroBotType.KARLSON.name,
                smartBotType = NeuroBotType.KARLSON,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = NeuroBotType.SCOOBY_DOO.name,
                smartBotType = NeuroBotType.SCOOBY_DOO,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = NeuroBotType.SPONGE_BOB.name,
                smartBotType = NeuroBotType.SPONGE_BOB,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = NeuroBotType.KUNG_FU_PANDA.name,
                smartBotType = NeuroBotType.KUNG_FU_PANDA,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = NeuroBotType.SHREK.name,
                smartBotType = NeuroBotType.SHREK,
                type = ShopDbModel.Type.INSTALLED
            ),
            ShopDbModel(
                id = NeuroBotType.JON_SNOW.name,
                smartBotType = NeuroBotType.JON_SNOW,
                type = ShopDbModel.Type.PAID
            ),
            ShopDbModel(
                id = NeuroBotType.TYRION_LANNISTER.name,
                smartBotType = NeuroBotType.TYRION_LANNISTER,
                type = ShopDbModel.Type.PAID
            )
        )
    }
}
