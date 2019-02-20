package com.smedialink.responses.data.repository

import android.content.Context
import com.smedialink.responses.data.database.BotDao
import com.smedialink.responses.data.database.BotsDatabase
import com.smedialink.responses.data.database.BotsInitial
import com.smedialink.responses.data.database.ShopDbModel
import com.smedialink.responses.data.factory.ActualResourceFactory
import com.smedialink.responses.data.network.SmartBotsApi
import com.smedialink.responses.domain.factory.ResourceFactory
import com.smedialink.responses.domain.model.NeuroBotType
import com.smedialink.responses.domain.model.SmartBot
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class SmartBotRepository(context: Context) {

    companion object {
        private var INSTANCE: SmartBotRepository? = null

        fun getInstance(context: Context): SmartBotRepository {
            if (INSTANCE == null) {
                INSTANCE = SmartBotRepository(context)
            }

            return INSTANCE!!
        }
    }

    private val botsDatabase: BotsDatabase = BotsDatabase.getInstance(context.applicationContext)
    private val botsApi: SmartBotsApi = SmartBotsApi.getInstance()

    private val botsDao: BotDao = botsDatabase.botDao()

    private val factory: ResourceFactory by lazy {
        ActualResourceFactory(context.assets)
    }

    fun getAllAvailable(): List<SmartBot> {

        cacheNewBotsIfAvailable()

        return botsDao.getAll()
            .filter { it.type == ShopDbModel.Type.INSTALLED }
            .map { SmartBot(factory, it.smartBotType) }
            .sortedBy { it.type.position }
    }


    fun cacheNewBotsIfAvailable(): Completable =
        Completable.create { emitter ->

            try {
                val allBots = BotsInitial.getAll()
                val cachedBots = botsDao.getAll()

                if (allBots.size != cachedBots.size) {
                    botsDao.insertButIgnore(allBots)
                }
            } catch (e: Exception) {
                emitter.onError(e)
            }

            emitter.onComplete()
        }

    fun savePurchases(purchases: List<NeuroBotType>): Completable =
        Completable.create { emitter ->
            val currentPaidBots = botsDao.getByInstallType(ShopDbModel.Type.PAID)
            val purchased = purchases.toSet()

            try {
                currentPaidBots.forEach { bot ->
                    if (purchased.contains(bot.smartBotType)) {
                        markAsInstalled(bot.smartBotType)
                    }
                }
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

    fun sendAppInstallEvent(userId: Long): Completable =
        botsApi.appInstall(
            appId = SmartBotsApi.CLIENT_ID,
            type = SmartBotsApi.TYPE_INSTALL,
            userId = userId
        ).flatMapCompletable { response ->
            when {
                response.status == SmartBotsApi.STATUS_OK ->
                    Completable.complete()
                response.status == SmartBotsApi.STATUS_ERROR ->
                    Completable.error(Exception(response.message))
                else ->
                    Completable.error(Exception("Unknown error"))
            }
        }

    fun sendBotInstallEvent(type: NeuroBotType, userId: Long): Completable {
        val installed = botsDao.getInstallEvent(type)

        if (installed == 1) {
            return Completable.complete()
        }

        return botsApi.botInstall(
            botId = type.label,
            type = SmartBotsApi.TYPE_INSTALL,
            userId = userId
        ).flatMapCompletable { response ->
            when {
                response.status == SmartBotsApi.STATUS_OK ->
                    Completable.complete()
                response.status == SmartBotsApi.STATUS_ERROR ->
                    Completable.error(Exception(response.message))
                else ->
                    Completable.error(Exception("Unknown error"))
            }
        }
            .doOnComplete { botsDao.saveInstallEvent(type) }
    }

    fun sendBotRating(type: NeuroBotType, userId: Long, rating: Int): Single<Int> =
        botsApi.voteForBot(
            botId = type.label,
            userId = userId,
            rating = rating
        ).flatMap { response ->
            when {
                response.status == SmartBotsApi.STATUS_OK ->
                    Single.just(rating)
                response.status == SmartBotsApi.STATUS_ERROR ->
                    Single.error(Exception(response.message))
                else ->
                    Single.error(Exception("Unknown error"))
            }
        }
            .doOnSuccess { botsDao.saveOwnRating(type, it) }
            .onErrorReturn { botsDao.getOwnRating(type) }

    fun fetchStatistics(): Completable =
        botsApi.getBotsInfo()
            .flatMapCompletable { response ->
                botsDao.saveStatistics(response)
                Completable.complete()
            }
            .subscribeOn(Schedulers.io())

    fun fetchVotes(userId: Long): Completable =
        botsApi.getBotsVoting(userId)
            .flatMapCompletable { response ->
                botsDao.saveRatings(response)
                Completable.complete()
            }
            .subscribeOn(Schedulers.io())

    private fun markAsInstalled(smartBotType: NeuroBotType) {
        val bot = botsDao.getByType(smartBotType).copy(type = ShopDbModel.Type.INSTALLED)
        botsDao.update(bot)
    }
}
