package com.smedialink.responses.data.database

import android.arch.persistence.room.*
import com.smedialink.responses.domain.model.enums.SmartBotType

@Dao
abstract class RecentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrReplace(entity: RecentDbModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrReplace(entities: List<RecentDbModel>): List<Long>

    @Query("SELECT counter FROM RecentDbModel WHERE smartBotType LIKE :type AND tag LIKE :tag AND position LIKE :position")
    abstract fun getCounter(type: SmartBotType, tag: String, position: Int): Int?

    @Query("SELECT position FROM RecentDbModel WHERE smartBotType LIKE :type AND tag LIKE :tag ORDER BY counter DESC")
    abstract fun getSortedPositions(type: SmartBotType, tag: String): List<Int>?

    @Transaction
    open fun refreshCounter(type: SmartBotType, tag: String, position: Int) {

        val current = getCounter(type, tag, position) ?: 0

        val newRecent = RecentDbModel(
            smartBotType = type,
            tag = tag,
            position = position,
            counter = current + 1
        )

        insertOrReplace(newRecent)
    }
}
