package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PositionProgressDao {
    @Query("SELECT * FROM position_progress")
    fun getAllProgress(): Flow<List<PositionProgressEntity>>

    @Query("SELECT * FROM position_progress WHERE positionId = :positionId LIMIT 1")
    fun getProgressForPosition(positionId: String): Flow<PositionProgressEntity?>

    @Query("SELECT * FROM position_progress WHERE positionId = :positionId LIMIT 1")
    suspend fun getProgressForPositionSync(positionId: String): PositionProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateProgress(progress: PositionProgressEntity)

    @Query("UPDATE position_progress SET isFavorite = :isFavorite, lastUpdated = :timestamp WHERE positionId = :positionId")
    suspend fun updateFavorite(positionId: String, isFavorite: Boolean, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE position_progress SET status = :status, lastUpdated = :timestamp WHERE positionId = :positionId")
    suspend fun updateStatus(positionId: String, status: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE position_progress SET notes = :notes, rating = :rating, lastUpdated = :timestamp WHERE positionId = :positionId")
    suspend fun updateNotesAndRating(positionId: String, notes: String, rating: Int, timestamp: Long = System.currentTimeMillis())
}
