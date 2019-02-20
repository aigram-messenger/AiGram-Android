package com.smedialink.responses.domain.model

import com.smedialink.responses.R

/**
 * Типы ботов и их ресурсы.
 * Если нужно, создать нового бота, то достаточно создать новый тип и прописать все необходимые свойства
 * Если бот платный, то нужно прописать sku, если бесплатный то = null
 * Временно, так как в будущем все боты будут на сервере
 */
enum class NeuroBotType : SmartBotProperties {
    TRADE_BINBANK {
        override val contentType: ContentType = ContentType.ADVERTS
        override val skuId: String? = null
        override val label: String = "binbank"
        override val title: Int = R.string.bot_title_trade_binbank
        override val description: Int = R.string.bot_description_trade_binbank
        override val wordSource: String = "bots/BinBank/words_binbank.json"
        override val responseSource: String = "bots/BinBank/response_binbank.json"
        override val modelAliasSource: String = "trade_binbank"
        override val modelPathSource: String = "bots/BinBank/binbankconverted_model.tflite"
        override val dateAdded: String = "22.01.2019"
        override val dateUpdated: String = "22.01.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 0f
        override val tags: List<BotTag> = emptyList()
    },
    // ВЕЛИКИЕ
    NAPOLEON {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = "bots.napoleon"
        override val label: String = "napoleon"
        override val title: Int = R.string.bot_title_napoleon
        override val description: Int = R.string.bot_description_napoleon
        override val wordSource: String = "bots/Napoleon/words_Napoleon.json"
        override val responseSource: String = "bots/Napoleon/response_Napoleon.json"
        override val modelAliasSource: String = "napoleon"
        override val modelPathSource: String = "bots/Napoleon/Napoleonconverted_model.tflite"
        override val dateAdded: String = "30.01.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 12f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.GREAT, BotTag.PAID, BotTag.MAN)
    },
    CHURCHILL {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = null
        override val label: String = "churchill"
        override val title: Int = R.string.bot_title_churchill
        override val description: Int = R.string.bot_description_churchill
        override val wordSource: String = "bots/Churchill/words_Churchill.json"
        override val responseSource: String = "bots/Churchill/response_Churchill.json"
        override val modelAliasSource: String = "churchill"
        override val modelPathSource: String = "bots/Churchill/Churchillconverted_model.tflite"
        override val dateAdded: String = "22.01.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 3f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.GREAT, BotTag.FREE, BotTag.MAN)
    },
    EINSTEIN {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = null
        override val label: String = "einstein"
        override val title: Int = R.string.bot_title_einstein
        override val description: Int = R.string.bot_description_einstein
        override val wordSource: String = "bots/Einstein/words_Einstein.json"
        override val responseSource: String = "bots/Einstein/response_Einstein.json"
        override val modelAliasSource: String = "einstein"
        override val modelPathSource: String = "bots/Einstein/Einsteinconverted_model.tflite"
        override val dateAdded: String = "22.01.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 2f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.GREAT, BotTag.FREE, BotTag.MAN)
    },
    CONFUCIUS {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = null
        override val label: String = "confucius"
        override val title: Int = R.string.bot_title_confucius
        override val description: Int = R.string.bot_description_confucius
        override val wordSource: String = "bots/Confucius/words_Confucius.json"
        override val responseSource: String = "bots/Confucius/response_Confucius.json"
        override val modelAliasSource: String = "confucius"
        override val modelPathSource: String = "bots/Confucius/Confuciusconverted_model.tflite"
        override val dateAdded: String = "22.01.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 1f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.GREAT, BotTag.FREE, BotTag.MAN)
    },
    // ИЗВЕСТНЫЕ
    CELENTANO {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = "bots.celentano"
        override val label: String = "celentano"
        override val title: Int = R.string.bot_title_celentano
        override val description: Int = R.string.bot_description_celentano
        override val wordSource: String = "bots/Celentano/words_Celentano.json"
        override val responseSource: String = "bots/Celentano/response_Celentano.json"
        override val modelAliasSource: String = "celentano"
        override val modelPathSource: String = "bots/Celentano/Celentanoconverted_model.tflite"
        override val dateAdded: String = "22.01.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 13f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.FAMOUS, BotTag.PAID, BotTag.MAN)
    },
    MONROE {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = null
        override val label: String = "monroe"
        override val title: Int = R.string.bot_title_monroe
        override val description: Int = R.string.bot_description_monroe
        override val wordSource: String = "bots/Monroe/words_Monroe.json"
        override val responseSource: String = "bots/Monroe/response_Monroe.json"
        override val modelAliasSource: String = "monroe"
        override val modelPathSource: String = "bots/Monroe/Monroeconverted_model.tflite"
        override val dateAdded: String = "22.01.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 4f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.FAMOUS, BotTag.FREE, BotTag.WOMAN)
    },
    CHANEL {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String = "bots.coco_chanel"
        override val label: String = "chanel"
        override val title: Int = R.string.bot_title_chanel
        override val description: Int = R.string.bot_description_chanel
        override val wordSource: String = "bots/Chanel/words_Chanel.json"
        override val responseSource: String = "bots/Chanel/response_Chanel.json"
        override val modelAliasSource: String = "chanel"
        override val modelPathSource: String = "bots/Chanel/Chanelconverted_model.tflite"
        override val dateAdded: String = "22.01.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 14f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.FAMOUS, BotTag.PAID, BotTag.WOMAN)
    },
    TSOY {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = "bots.tsoy"
        override val label: String = "tsoy"
        override val title: Int = R.string.bot_title_tsoy
        override val description: Int = R.string.bot_description_tsoy
        override val wordSource: String = "bots/Tsoy/words_Tsoy.json"
        override val responseSource: String = "bots/Tsoy/response_Tsoy.json"
        override val modelAliasSource: String = "tsoy"
        override val modelPathSource: String = "bots/Tsoy/Tsoyconverted_model.tflite"
        override val dateAdded: String = "30.01.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 15f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.FAMOUS, BotTag.PAID, BotTag.MAN)
    },
    TERESA {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = "bots.teresa"
        override val label: String = "teresa"
        override val title: Int = R.string.bot_title_teresa
        override val description: Int = R.string.bot_description_teresa
        override val wordSource: String = "bots/Teresa/words_Teresa.json"
        override val responseSource: String = "bots/Teresa/response_Teresa.json"
        override val modelAliasSource: String = "teresa"
        override val modelPathSource: String = "bots/Teresa/Teresaconverted_model.tflite"
        override val dateAdded: String = "30.01.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 16f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.FAMOUS, BotTag.PAID, BotTag.WOMAN)
    },
    // КИНОГЕРОИ
    YODA {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = null
        override val label: String = "yoda"
        override val title: Int = R.string.bot_title_yoda
        override val description: Int = R.string.bot_description_yoda
        override val wordSource: String = "bots/Yoda/words_Yoda.json"
        override val responseSource: String = "bots/Yoda/response_Yoda.json"
        override val modelAliasSource: String = "yoda"
        override val modelPathSource: String = "bots/Yoda/Yodaconverted_model.tflite"
        override val dateAdded: String = "22.01.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 5f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.HERO_MOVIE, BotTag.FREE, BotTag.MAN)
    },
    TERMINATOR {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = "bots.terminator"
        override val label: String = "terminator"
        override val title: Int = R.string.bot_title_terminator
        override val description: Int = R.string.bot_description_terminator
        override val wordSource: String = "bots/Terminator/words_Terminator.json"
        override val responseSource: String = "bots/Terminator/response_Terminator.json"
        override val modelAliasSource: String = "terminator"
        override val modelPathSource: String = "bots/Terminator/Terminatorconverted_model.tflite"
        override val dateAdded: String = "22.01.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 17f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.HERO_MOVIE, BotTag.PAID, BotTag.MAN)
    },
    DEADPOOL {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = null
        override val label: String = "deadpool"
        override val title: Int = R.string.bot_title_deadpool
        override val description: Int = R.string.bot_description_deadpool
        override val wordSource: String = "bots/Deadpool/words_Deadpool.json"
        override val responseSource: String = "bots/Deadpool/response_Deadpool.json"
        override val modelAliasSource: String = "deadpool"
        override val modelPathSource: String = "bots/Deadpool/Deadpoolconverted_model.tflite"
        override val dateAdded: String = "15.02.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 6f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.HERO_MOVIE, BotTag.FREE, BotTag.MAN)
    },
    DAENERYS {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = "bots.daenerys"
        override val label: String = "daenerys"
        override val title: Int = R.string.bot_title_daenerys
        override val description: Int = R.string.bot_description_daenerys
        override val wordSource: String = "bots/Daenerys/words_Daenerys.json"
        override val responseSource: String = "bots/Daenerys/response_Daenerys.json"
        override val modelAliasSource: String = "daenerys"
        override val modelPathSource: String = "bots/Daenerys/Daenerysconverted_model.tflite"
        override val dateAdded: String = "02.02.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 18f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.HERO_MOVIE, BotTag.PAID, BotTag.WOMAN)
    },
    TYRION_LANNISTER {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = "bots.tyrion"
        override val label: String = "lannister"
        override val title: Int = R.string.bot_title_tyrion
        override val description: Int = R.string.bot_description_tyrion
        override val wordSource: String = "bots/Tyrion/words_Tyrion.json"
        override val responseSource: String = "bots/Tyrion/response_Tyrion.json"
        override val modelAliasSource: String = "tyrion"
        override val modelPathSource: String = "bots/Tyrion/Tyrionconverted_model.tflite"
        override val dateAdded: String = "02.02.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 19f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.HERO_MOVIE, BotTag.PAID, BotTag.MAN)
    },
    JON_SNOW {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = "bots.jon_snow"
        override val label: String = "snow"
        override val title: Int = R.string.bot_title_jon_snow
        override val description: Int = R.string.bot_description_jon_snow
        override val wordSource: String = "bots/Snow/words_Snow.json"
        override val responseSource: String = "bots/Snow/response_Snow.json"
        override val modelAliasSource: String = "snow"
        override val modelPathSource: String = "bots/Snow/Snowconverted_model.tflite"
        override val dateAdded: String = "02.02.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 20f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.HERO_MOVIE, BotTag.PAID, BotTag.MAN)
    },
    CORLEONE {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = "bots.don_corleone"
        override val label: String = "corleone"
        override val title: Int = R.string.bot_title_corleone
        override val description: Int = R.string.bot_description_corleone
        override val wordSource: String = "bots/Corleone/words_Corleone.json"
        override val responseSource: String = "bots/Corleone/response_Corleone.json"
        override val modelAliasSource: String = "corleone"
        override val modelPathSource: String = "bots/Corleone/Corleoneconverted_model.tflite"
        override val dateAdded: String = "22.01.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 21f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.HERO_MOVIE, BotTag.PAID, BotTag.MAN)
    },
    // МУЛЬТГЕРОИ
    SPONGE_BOB {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = "bots.sponge_bob"
        override val label: String = "spongebob"
        override val title: Int = R.string.bot_title_sponge_bob
        override val description: Int = R.string.bot_description_sponge_bob
        override val wordSource: String = "bots/Sponge_Bob/words_Sponge_Bob.json"
        override val responseSource: String = "bots/Sponge_Bob/response_Sponge_Bob.json"
        override val modelAliasSource: String = "spongebob"
        override val modelPathSource: String = "bots/Sponge_Bob/Sponge_Bobconverted_model.tflite"
        override val dateAdded: String = "30.01.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 22f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.HERO_CARTOON, BotTag.PAID, BotTag.MAN)
    },
    KARLSON {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = "bots.karlson"
        override val label: String = "karlson"
        override val title: Int = R.string.bot_title_karlson
        override val description: Int = R.string.bot_description_karlson
        override val wordSource: String = "bots/Karlson/words_Karlson.json"
        override val responseSource: String = "bots/Karlson/response_Karlson.json"
        override val modelAliasSource: String = "karlson"
        override val modelPathSource: String = "bots/Karlson/Karlsonconverted_model.tflite"
        override val dateAdded: String = "30.01.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 23f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.HERO_CARTOON, BotTag.PAID, BotTag.MAN)
    },
    SCOOBY_DOO {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = null
        override val label: String = "scoobydoo"
        override val title: Int = R.string.bot_title_scooby_doo
        override val description: Int = R.string.bot_description_scooby_doo
        override val wordSource: String = "bots/Scooby_Doo/words_Scooby_Doo.json"
        override val responseSource: String = "bots/Scooby_Doo/response_Scooby_Doo.json"
        override val modelAliasSource: String = "scoobydoo"
        override val modelPathSource: String = "bots/Scooby_Doo/Scooby_Dooconverted_model.tflite"
        override val dateAdded: String = "30.01.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 8f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.HERO_CARTOON, BotTag.FREE, BotTag.MAN)
    },
    KUNG_FU_PANDA {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = null
        override val label: String = "kung_fu_panda"
        override val title: Int = R.string.bot_title_panda
        override val description: Int = R.string.bot_description_panda
        override val wordSource: String = "bots/Panda/words_Panda.json"
        override val responseSource: String = "bots/Panda/response_Panda.json"
        override val modelAliasSource: String = "panda"
        override val modelPathSource: String = "bots/Panda/Pandaconverted_model.tflite"
        override val dateAdded: String = "06.02.2019"
        override val dateUpdated: String = "06.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 9f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.HERO_CARTOON, BotTag.FREE, BotTag.MAN)
    },
    SHREK {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = null
        override val label: String = "shrek"
        override val title: Int = R.string.bot_title_shrek
        override val description: Int = R.string.bot_description_shrek
        override val wordSource: String = "bots/Shrek/words_Shrek.json"
        override val responseSource: String = "bots/Shrek/response_Shrek.json"
        override val modelAliasSource: String = "shrek"
        override val modelPathSource: String = "bots/Shrek/Shrekconverted_model.tflite"
        override val dateAdded: String = "06.02.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 7f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.HERO_CARTOON, BotTag.FREE, BotTag.MAN)
    },
    // СБОРНИКИ
    SOVIET_FILMS {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = null
        override val label: String = "soviet_films"
        override val title: Int = R.string.bot_title_soviet_films
        override val description: Int = R.string.bot_description_soviet_films
        override val wordSource: String = "bots/Soviet_films/words_Soviet_films.json"
        override val responseSource: String = "bots/Soviet_films/response_Soviet_films.json"
        override val modelAliasSource: String = "soviet_films"
        override val modelPathSource: String = "bots/Soviet_films/Soviet_filmsconverted_model.tflite"
        override val dateAdded: String = "22.01.2019"
        override val dateUpdated: String = "15.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 10f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.PACK, BotTag.FREE, BotTag.MAN, BotTag.WOMAN)
    },
    MEMES {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = null
        override val label: String = "memes"
        override val title: Int = R.string.bot_title_memes
        override val description: Int = R.string.bot_description_memes
        override val wordSource: String = "bots/Memes/words_Memes.json"
        override val responseSource: String = "bots/Memes/response_Memes.json"
        override val modelAliasSource: String = "memes"
        override val modelPathSource: String = "bots/Memes/Memesconverted_model.tflite"
        override val dateAdded: String = "22.01.2019"
        override val dateUpdated: String = "22.01.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 11f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.PACK, BotTag.FREE, BotTag.MAN, BotTag.WOMAN)
    },
    ASSISTANT {
        override val contentType: ContentType = ContentType.BOT
        override val skuId: String? = null
        override val label: String = "assistant"
        override val title: Int = R.string.bot_title_assistant
        override val description: Int = R.string.bot_description_assistant
        override val wordSource: String = "bots/Assistant/words_Assistant.json"
        override val responseSource: String = "bots/Assistant/response_Assistant.json"
        override val modelAliasSource: String = "assistant"
        override val modelPathSource: String = "bots/Assistant/Assistantconverted_model.tflite"
        override val dateAdded: String = "11.02.2019"
        override val dateUpdated: String = "11.02.2019"
        override val themesCount: Long = 49
        override val phrasesCount: Long = 545
        override val position: Float = 9.5f
        override val tags: List<BotTag> =
                arrayListOf(BotTag.PACK, BotTag.FREE, BotTag.MAN, BotTag.WOMAN)
    };

    companion object {
        // Новых ботов вносим сюда
        // TODO чекер новых ботов
        private val newBots = setOf(
                SHREK,
                KUNG_FU_PANDA,
                JON_SNOW,
                TYRION_LANNISTER,
                ASSISTANT,
                DEADPOOL
        )

        fun resolve(name: String): NeuroBotType? {
            return values().find { it.name == name }
        }


        fun resolveFromLabel(label: String): NeuroBotType? {
            return values().find { it.label == label }
        }

        fun resolveFromSku(sku: String): NeuroBotType? {
            return values().find { it.skuId == sku }
        }

        fun skus(): List<String> {
            return NeuroBotType.values().mapNotNull { it.skuId }
        }

        fun isNew(type: NeuroBotType): Boolean {
            return newBots.contains(type)
        }
    }
}

enum class BotTag(val res: Int) {
    FREE(R.string.bot_tag_free),
    PAID(R.string.bot_tag_paid),
    MAN(R.string.bot_tag_man),
    WOMAN(R.string.bot_tag_woman),
    GREAT(R.string.bot_tag_great),
    FAMOUS(R.string.bot_tag_famous),
    HERO_MOVIE(R.string.bot_tag_hero_movie),
    HERO_CARTOON(R.string.bot_tag_hero_cartoon),
    PACK(R.string.bot_tag_pack);

    companion object {
        fun resolve(name: String): BotTag? {
            return values().find { it.name == name }
        }
    }
}

enum class ContentType {
    ADVERTS, BOT
}

interface SmartBotProperties {
    val label: String
    val contentType: ContentType
    val skuId: String?
    val title: Int
    val description: Int
    val wordSource: String
    val responseSource: String
    val modelAliasSource: String
    val modelPathSource: String
    val dateAdded: String
    val dateUpdated: String
    val themesCount: Long
    val phrasesCount: Long
    val position: Float
    val tags: List<BotTag>
}

