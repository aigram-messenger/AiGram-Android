package com.smedialink.responses.data.database

import android.arch.persistence.room.*
import com.smedialink.responses.data.network.BotRemoteInfo
import com.smedialink.responses.data.network.BotVoteInfo
import com.smedialink.responses.data.network.Response
import com.smedialink.responses.data.network.SmartBotsApi
import com.smedialink.responses.domain.model.enums.SmartBotType
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
abstract class BotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrReplace(entity: ShopDbModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrReplace(entities: List<ShopDbModel>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertButIgnore(entities: List<ShopDbModel>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    protected abstract fun insert(entity: ShopDbModel): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    protected abstract fun insert(entities: List<ShopDbModel>): List<Long>

    @Update
    abstract fun update(entity: ShopDbModel)

    @Update
    abstract fun update(entities: List<ShopDbModel>)

    @Transaction
    open fun upsert(entity: ShopDbModel) {
        val id = insert(entity)
        if (id == -1L) {
            update(entity)
        }
    }

    @Transaction
    open fun upsert(entities: List<ShopDbModel>) {
        val insertResult = insert(entities)

        val updateList = entities.filterIndexed { i, _ ->
            insertResult[i] == -1L
        }

        if (!updateList.isEmpty()) {
            update(updateList)
        }
    }

    @Delete
    abstract fun delete(entity: ShopDbModel)

    @Delete
    abstract fun delete(entities: List<ShopDbModel>)

    @Query("SELECT * FROM ShopDbModel")
    abstract fun streamAll(): Flowable<List<ShopDbModel>>

    @Query("SELECT * FROM ShopDbModel WHERE id LIKE :id")
    abstract fun getById(id: Long): Single<ShopDbModel>

    @Query("SELECT * FROM ShopDbModel WHERE smartBotType LIKE :smartBotType")
    abstract fun getByType(smartBotType: SmartBotType): ShopDbModel

    @Query("SELECT * FROM ShopDbModel WHERE smartBotType LIKE :smartBotType")
    abstract fun getBySmartBotType(smartBotType: SmartBotType): Flowable<ShopDbModel>

    @Query("SELECT * FROM ShopDbModel WHERE type LIKE :installType")
    abstract fun getByInstallType(installType: ShopDbModel.Type): List<ShopDbModel>

    @Query("SELECT * FROM ShopDbModel ")
    abstract fun getAll(): List<ShopDbModel>

    @Query("UPDATE ShopDbModel SET installs = :value WHERE smartBotType = :smartBotType")
    abstract fun saveInstalls(smartBotType: SmartBotType, value: Long): Long

    @Query("UPDATE ShopDbModel SET ratings = :value WHERE smartBotType = :smartBotType")
    abstract fun saveRatings(smartBotType: SmartBotType, value: Long): Long

    @Query("UPDATE ShopDbModel SET averageRating = :value WHERE smartBotType = :smartBotType")
    abstract fun saveAverage(smartBotType: SmartBotType, value: Float): Long

    @Query("UPDATE ShopDbModel SET phrases = :value WHERE smartBotType = :smartBotType")
    abstract fun savePhrases(smartBotType: SmartBotType, value: Long): Long

    @Query("UPDATE ShopDbModel SET themes = :value WHERE smartBotType = :smartBotType")
    abstract fun saveThemes(smartBotType: SmartBotType, value: Long): Long

    @Query("UPDATE ShopDbModel SET ownRating = :value WHERE smartBotType = :smartBotType")
    abstract fun saveOwnRating(smartBotType: SmartBotType, value: Int): Long

    @Query("SELECT ownRating FROM ShopDbModel WHERE type LIKE :smartBotType")
    abstract fun getOwnRating(smartBotType: SmartBotType): Int

    @Query("UPDATE ShopDbModel SET installEventLogged = 1 WHERE smartBotType = :smartBotType")
    abstract fun saveInstallEvent(smartBotType: SmartBotType): Long

    @Query("SELECT installEventLogged FROM ShopDbModel WHERE type LIKE :smartBotType")
    abstract fun getInstallEvent(smartBotType: SmartBotType): Int

    @Transaction
    open fun saveStatistics(response: Response<List<BotRemoteInfo>>) {
        if (response.status.toLowerCase() == SmartBotsApi.STATUS_OK) {
            response.payload.forEach { bot ->
                SmartBotType.resolveFromLabel(bot.name)?.let { type ->
                    saveInstalls(type, bot.installs.toLong())
                    saveRatings(type, bot.votings.toLong())
                    saveAverage(type, bot.rating.toFloat())
                    savePhrases(type, bot.phrases.toLong())
                    saveThemes(type, bot.themes.toLong())
                }
            }
        }
    }

    @Transaction
    open fun saveRatings(response: Response<List<BotVoteInfo>>) {
        if (response.status.toLowerCase() == SmartBotsApi.STATUS_OK) {
            response.payload.forEach { bot ->
                SmartBotType.resolveFromLabel(bot.name)?.let { type ->
                    saveOwnRating(type, bot.rating.toInt())
                }
            }
        }
    }
}