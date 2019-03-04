package com.smedialink.responses.data

import android.content.Context
import com.smedialink.responses.data.repository.AnswersStorageRepository
import com.smedialink.responses.data.repository.SmartBotRepository
import com.smedialink.responses.domain.Replier
import com.smedialink.responses.domain.model.enums.SmartBotType
import com.smedialink.responses.domain.model.response.SmartBotResponse
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
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

    private val answersRepository: Deferred<AnswersStorageRepository> =
        async {
            withContext(Dispatchers.IO) {
                AnswersStorageRepository.getInstance(context)
            }
        }

    private val currentYearTag: String by lazy {
        SimpleDateFormat("yyyy", Locale.getDefault()).format(Date())
    }

    override fun getAvailableResponses(sentence: String, userId: Int, callback: Replier.Callback) {
        launch {
            try {
                val lemmas = sentence.split(Regex("[.,?!:; ]"))
                    .asSequence()
                    .filter { it.isNotEmpty() }
                    .map { it.toLowerCase() }
                    .toList()

                val detected = mutableListOf<SmartBotResponse>()
                val result = mutableListOf<SmartBotResponse>()

                withContext(Dispatchers.IO) {
                    botsRepository.await().getAllAvailable().forEach { bot ->
                        bot.getResponse(lemmas)?.let { response ->
                            detected.add(response)
                        }
                    }

                    val answers = answersRepository.await()
                    result.addAll(buildBotsList(detected, answers, userId))
                }

                callback.onSuccess(result)

            } catch (e: Exception) {
                callback.onError(e)
            }
        }
    }

    private fun buildBotsList(responses: List<SmartBotResponse>, repository: AnswersStorageRepository, userId: Int): List<SmartBotResponse> {
        val assistant = responses.firstOrNull { it.type == SmartBotType.ASSISTANT }
        val holidays = responses.firstOrNull { it.type == SmartBotType.HOLIDAYS }

        val others = responses
            .filterNot { it.type == SmartBotType.ASSISTANT }
            .filterNot { it.type == SmartBotType.HOLIDAYS }

        val recentAnswersList = mutableListOf<Pair<Int, String>>()

        (listOf(assistant) + others).forEach { response ->
            if (response != null) {
                val maxPosition = repository.getPositionWithMaxCounter(response.type, response.tag)
//                Log.d("Counters", "Detected max position $max for ${response.type.label} with tag ${response.tag}")
                if (maxPosition != -1) {
                    val maxCounter = repository.getCounterForPosition(response.type, response.tag, maxPosition)
                    recentAnswersList.add(Pair(maxCounter, response.phrases[maxPosition]))
                }
            }
        }

        var recent: SmartBotResponse? = null

        if (recentAnswersList.isNotEmpty()) {

            val sortedList = recentAnswersList.sortedByDescending { it.first }
            val sortedAnswers = sortedList.map { it.second }

            recent = SmartBotResponse(
                type = SmartBotType.RECENT,
                tag = "",
                link = "",
                phrases = sortedAnswers
            )
        }

        val fullTag = "${holidays?.tag}.$currentYearTag"
        val congratulated = repository.getTagsForUser(userId)

        return if (congratulated.contains(fullTag)) {
            (listOf(recent, assistant) + others).filterNotNull()
        } else {
            (listOf(recent, holidays, assistant) + others).filterNotNull()
        }
    }

    override fun cancel() {
        job.cancelChildren()
    }
}
