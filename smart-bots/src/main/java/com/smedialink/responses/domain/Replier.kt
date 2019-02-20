package com.smedialink.responses.domain

import com.smedialink.responses.domain.model.SmartBotResponse
import org.jetbrains.annotations.NotNull

interface Replier {

    interface Callback {

        /**
         * Коллбек успешного получения ответов от ботов
         *
         * @param responses Список ответов от ботов
         */
        fun onSuccess(@NotNull responses: List<SmartBotResponse>)

        /**
         * Коллбек возникновения исключения в процессе получения ответов от ботов
         *
         * @param throwable Пойманное исключение
         */
        fun onError(@NotNull throwable: Throwable)
    }

    /**
     * Основной метод получения ответов от ботов
     *
     * @param lemmas Исходное предложение в виде списка лемматизированных слов
     * @param callback Коллбек для получения результатов
     */
    fun getAvailableResponses(sentence: String, callback: Callback)

    /**
     * Метод для отмены всех запущенных внутри ботов корутин
     */
    fun cancel()
}
