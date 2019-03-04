package com.smedialink.responses.data.factory

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smedialink.responses.domain.factory.ResourceFactory
import com.smedialink.responses.domain.model.response.SmartBotResponseBlock
import com.smedialink.responses.domain.model.response.SmartBotResponse
import com.smedialink.responses.domain.model.enums.SmartBotType
import com.smedialink.responses.extensions.asString

class ActualResourceFactory(
        private val assetManager: AssetManager
) : ResourceFactory {

    private val gson: Gson by lazy {
        Gson()
    }
    override fun getWordsBag(type: SmartBotType): Map<Int, String> {
        val wordsString = type.wordSource.let { assetManager.open(it).asString() }
        val wordsType = object : TypeToken<List<String>>() {}.type
        val wordsList: List<String> = gson.fromJson(wordsString, wordsType)
        return wordsList.mapIndexed { index, str -> index to str }.toMap()
    }

    override fun getResponsesList(type: SmartBotType): List<SmartBotResponse> {
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

    override fun getMlModelAlias(type: SmartBotType): String = type.modelAliasSource

    override fun getMlModelPath(type: SmartBotType): String = type.modelPathSource
}
