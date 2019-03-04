package com.smedialink.responses.domain.factory

import com.smedialink.responses.domain.model.response.SmartBotResponse
import com.smedialink.responses.domain.model.enums.SmartBotType

interface ResourceFactory {

    /**
     * Получение мешка слов, известных данному боту
     *
     * @param type Тип бота
     *
     * @return Мапа слов, где ключ это позиция слова, а значение это само слово
     */
    fun getWordsBag(type: SmartBotType): Map<Int, String>

    /**
     * Получение списка ответов, известных данному боту
     *
     * @param type Тип бота
     *
     * @return Список ответов
     */
    fun getResponsesList(type: SmartBotType): List<SmartBotResponse>

    /**
     * Получение псевдонима ML модели, требуется для инициализации конкретного бота в MlKit
     *
     * @param type Тип бота
     *
     * @return Псевдоним модели
     */
    fun getMlModelAlias(type: SmartBotType): String

    /**
     * Получение пути к файлу ML модели, требуется для инициализации конкретного бота в MlKit
     *
     * @param type Тип бота
     *
     * @return Путь к файлу ML модели в папке assets
     */
    fun getMlModelPath(type: SmartBotType): String
}
