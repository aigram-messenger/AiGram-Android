package com.smedialink.responses.data

import android.content.Context
import com.smedialink.responses.data.repository.SmartBotRepository
import com.smedialink.responses.domain.Replier
import com.smedialink.responses.domain.model.NeuroBotType
import com.smedialink.responses.domain.model.SmartBotResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SmartReplier(context: Context) : Replier, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val job: Job = Job()

    private val botsRepository: Deferred<SmartBotRepository> =
            async {
                withContext(Dispatchers.IO) {
                    SmartBotRepository.getInstance(context)
                }
            }

    override fun getAvailableResponses(sentence: String, callback: Replier.Callback) {
        launch {
            try {
                val lemmas = sentence.split(Regex("[.,?!:; ]"))
                        .asSequence()
                        .filter { it.isNotEmpty() }
                        .map { it.toLowerCase() }
                        .toList()

                val result = mutableListOf<SmartBotResponse>()

                withContext(Dispatchers.IO) {
                    botsRepository.await().getAllAvailable().forEach { bot ->
                        bot.getResponse(lemmas)?.let { response ->
                            result.add(response)
                        }
                    }
                }

                val sorted = checkForAssistant(result)
                callback.onSuccess(sorted)

            } catch (e: Exception) {
                callback.onError(e)
            }
        }
    }

    private fun checkForAssistant(list: MutableList<SmartBotResponse>): List<SmartBotResponse> {
        val position = list.indexOfFirst { it.type == NeuroBotType.ASSISTANT }
        return if (position != -1) {
            val assistant = list.first { it.type == NeuroBotType.ASSISTANT }
            val others = list.filterNot { it.type == NeuroBotType.ASSISTANT }
            listOf(assistant) + others
        } else {
            list
        }
    }

    override fun cancel() {
        job.cancelChildren()
    }
}
