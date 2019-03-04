package com.smedialink.responses.data.repository

import android.content.Context
import com.smedialink.responses.data.database.BotsDatabase
import com.smedialink.responses.data.database.HolidaysDao
import com.smedialink.responses.data.database.RecentDao
import com.smedialink.responses.domain.model.enums.SmartBotType
import java.text.SimpleDateFormat
import java.util.*

class AnswersStorageRepository(context: Context) {

    companion object {
        private var INSTANCE: AnswersStorageRepository? = null

        fun getInstance(context: Context): AnswersStorageRepository {
            if (INSTANCE == null) {
                INSTANCE = AnswersStorageRepository(context)
            }

            return INSTANCE!!
        }
    }

    private val yearTag: String by lazy {
        SimpleDateFormat("YYYY", Locale.getDefault()).format(Date())
    }

    private val botsDatabase: BotsDatabase = BotsDatabase.getInstance(context.applicationContext)
    private val holidaysDao: HolidaysDao = botsDatabase.holidaysDao()
    private val recentDao: RecentDao = botsDatabase.recentDao()

    fun getTagsForUser(userId: Int): Set<String> {
        return holidaysDao.getTagsForUser(userId)
    }

    fun saveHolidayGreeting(userId: Int, tag: String) {
        val newTag = "$tag.$yearTag"
        holidaysDao.saveForUser(userId, newTag)
    }

    fun saveResponseCounter(type: SmartBotType, tag: String, position: Int) {
        recentDao.refreshCounter(type, tag, position)
    }

    fun getPositionWithMaxCounter(type: SmartBotType, tag: String): Int {
        return recentDao.getSortedPositions(type, tag)?.firstOrNull() ?: -1
    }

    fun getCounterForPosition(type: SmartBotType, tag: String, position: Int): Int {
        return recentDao.getCounter(type, tag, position) ?: 0
    }
}
