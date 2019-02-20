package com.smedialink.responses.data.factory

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smedialink.responses.domain.factory.ResourceFactory
import com.smedialink.responses.domain.model.SmartBotResponseBlock
import com.smedialink.responses.domain.model.SmartBotResponse
import com.smedialink.responses.domain.model.NeuroBotType
import com.smedialink.responses.extensions.asString

class ActualResourceFactory(
        private val assetManager: AssetManager
) : ResourceFactory {

    private val gson: Gson by lazy {
        Gson()
    }

    // TODO Стандартизировать имена файлов и парсить содержимое папки assets

    override fun getWordsBag(type: NeuroBotType): Map<Int, String> {
        val wordsString = type.wordSource.let { assetManager.open(it).asString() }
        val wordsType = object : TypeToken<List<String>>() {}.type
        val wordsList: List<String> = gson.fromJson(wordsString, wordsType)
        return wordsList.mapIndexed { index, str -> index to str }.toMap()
    }

    override fun getResponsesList(type: NeuroBotType): List<SmartBotResponse> {
        val responsesString = type.responseSource.let { assetManager.open(it).asString() }
        val responsesType = object : TypeToken<List<SmartBotResponseBlock>>() {}.type
        val responsesList: List<SmartBotResponseBlock> = gson.fromJson(responsesString, responsesType)
        return responsesList.map { item ->
            SmartBotResponse(
                    type = type,
                    tag = item.tag,
                    link = item.link,
                    phrases = item.response
            )
        }
    }

    override fun getMlModelAlias(type: NeuroBotType): String = type.modelAliasSource

    override fun getMlModelPath(type: NeuroBotType): String = type.modelPathSource
}
