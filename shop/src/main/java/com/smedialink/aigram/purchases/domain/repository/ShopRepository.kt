package com.smedialink.aigram.purchases.domain.repository

import android.content.Context
import com.smedialink.responses.data.database.BotDao
import com.smedialink.responses.data.database.BotsDatabase
import com.smedialink.responses.data.database.ShopDbModel
import com.smedialink.responses.domain.model.enums.SmartBotType
import com.smedialink.responses.event.SmartBotEventBus
import com.smedialink.responses.event.SmartBotEventBus.Event
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by Oleg Sheliakin on 15.01.2019.
 * Contact me by email - olegsheliakin@gmail.com
 */

class ShopRepository(context: Context) {

    private val botsDatabase: BotsDatabase =
        BotsDatabase.getInstance(context.applicationContext)

    private val botsDao: BotDao by lazy {
        return@lazy botsDatabase.botDao()
    }

    private val botEvents: BehaviorSubject<Event> by lazy {
        SmartBotEventBus.get()
    }

    fun getByType(smartBotType: SmartBotType): Flowable<ShopDbModel> {
        return botsDao.getBySmartBotType(smartBotType)
    }

    fun getProducts(): Flowable<List<ShopDbModel>> {
        return botsDao.streamAll()
    }

    fun markEnabled(smartBotType: SmartBotType, refreshPanel: Boolean): Completable {
        return botsDao.getBySmartBotType(smartBotType)
            .firstOrError()
            .doOnSuccess {
                botsDao.update(it.copy(type = ShopDbModel.Type.ENABLED))
                botEvents.onNext(Event.BotsRefreshed(refreshPanel))
            }
            .toCompletable()
    }

    fun markInstalled(smartBotType: SmartBotType, refreshPanel: Boolean): Completable {
        return botsDao.getBySmartBotType(smartBotType)
            .firstOrError()
            .doOnSuccess {
                botsDao.update(it.copy(type = ShopDbModel.Type.INSTALLED))
                botEvents.onNext(Event.BotInstalled(smartBotType))
                botEvents.onNext(Event.BotsRefreshed(refreshPanel))
            }
            .toCompletable()
    }
}