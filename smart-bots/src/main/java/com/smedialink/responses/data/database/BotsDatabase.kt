package com.smedialink.responses.data.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.smedialink.responses.data.database.converter.Converter
import java.util.concurrent.Executors

@Database(
    entities = [
        ShopDbModel::class,
        HolidaysDbModel::class,
        RecentDbModel::class
    ],
    version = 6,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class BotsDatabase : RoomDatabase() {

    abstract fun botDao(): BotDao
    abstract fun holidaysDao(): HolidaysDao
    abstract fun recentDao(): RecentDao

    companion object {
        private var INSTANCE: BotsDatabase? = null

        fun getInstance(context: Context): BotsDatabase {
            if (INSTANCE == null) {
                synchronized(BotsDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            BotsDatabase::class.java, "bots.db")
                            .addCallback(object : Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    Executors.newSingleThreadScheduledExecutor().execute {
                                        getInstance(context).botDao().insertOrReplace(BotsInitial.getAll())
                                    }
                                }
                            })
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}
