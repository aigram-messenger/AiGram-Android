package com.smedialink.responses.domain.model

import com.google.firebase.ml.custom.*
import com.google.firebase.ml.custom.model.FirebaseLocalModelSource
import com.smedialink.responses.domain.factory.ResourceFactory
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Основной класс ботов
 */
class SmartBot(private val factory: ResourceFactory, val type: NeuroBotType) {

    // Интерпретатор ML модели
    private val interpreter: FirebaseModelInterpreter? by lazy {
        val options = FirebaseModelOptions.Builder()
            .setLocalModelName(factory.getMlModelAlias(type))
            .build()

        FirebaseModelInterpreter.getInstance(options)
    }

    // Список известных боту слов (мешок слов), загружается из json
    private val words: Map<Int, String> =
        factory.getWordsBag(type)

    // Список известных боту ответов, загружается из json
    private val responses: List<SmartBotResponse> =
        factory.getResponsesList(type)

    init {
        // Регистрация локальной ML модели и загрузка модели из assets
        FirebaseLocalModelSource.Builder(factory.getMlModelAlias(type))
            .setAssetFilePath(factory.getMlModelPath(type))
            .build()
            .also { FirebaseModelManager.getInstance().registerLocalModelSource(it) }
    }

    /**
     * Получение ответа от бота
     *
     * @param lemmas Лемматизированное предложение
     * (входящее сообщение пропущенное через нормализатор)
     *
     * @return Ответ бота
     */
    suspend fun getResponse(lemmas: List<String>): SmartBotResponse? {

        val result = mutableListOf<SmartBotResponse>()

        val inputArray = prepareInput(lemmas, words)

        // Входные данные ML модели
        val inputs = FirebaseModelInputs.Builder()
            .add(inputArray)
            .build()

        // Опции входных данных ML модели
        // Input должен быть равен размеру мешка слов бота
        // Оutput должен быть равен размеру списка известных боту ответов
        // Тип всегда FLOAT32
        val inputOutputOptions: FirebaseModelInputOutputOptions = FirebaseModelInputOutputOptions.Builder()
            .setInputFormat(0, FirebaseModelDataType.FLOAT32, intArrayOf(1, words.size))
            .setOutputFormat(0, FirebaseModelDataType.FLOAT32, intArrayOf(1, responses.size))
            .build()

        val outputs = getOutputsFromModel(inputs, inputOutputOptions)
        val outputsArray = outputs as Array<*>

        (outputsArray[0] as FloatArray).forEachIndexed { index, value ->
            result.add(responses[index].apply { probability = value })
        }

        return result
            .filter { it.probability > PROBABILITY_THRESHOLD }
            .sortedByDescending { it.probability }
            .firstOrNull()
    }

    // Получение ответа от интерпретатора ML модели
    private suspend fun getOutputsFromModel(input: FirebaseModelInputs, options: FirebaseModelInputOutputOptions): Any =
        suspendCoroutine { continuation ->
            interpreter?.run(input, options)
                ?.addOnSuccessListener { result: FirebaseModelOutputs ->
                    continuation.resume(result.getOutput(0))
                }
                ?.addOnFailureListener { exception: Exception ->
                    continuation.resumeWithException(exception)
                }
        }

    /**
     * Метод для подготовки входных данных, требуемых для работы ML модели
     *
     * @param lemmas Исходное предложение в виде списка лемм
     * @param bag Мешок слов бота
     *
     * @return Массив из 0 и 1
     *
     * Размер массива равен размеру мешка слов.
     * Если слово из списка лемм имеется в мешке слов, то i-элементу массива присваиваем 1, иначе 0
     * i это позиция найденного слова в мешке слов
     * Из-за особенности модели возвращаемый результат должен быть массивом из одного элемента,
     * который тоже является массивом
     */
    private fun prepareInput(lemmas: List<String>, bag: Map<Int, String>): Array<FloatArray> {

        val result = FloatArray(bag.size)
        val lemmaSet = lemmas.toSet()

        for (entry in bag) {
            if (lemmaSet.contains(entry.value))
                result[entry.key] = 1.0f
        }

        return arrayOf(result)
    }

    // Порог вероятности, выше которого ответ считается успешным, от 0.0 до 1.0
    private companion object {
        const val PROBABILITY_THRESHOLD = 0.9f
    }
}
