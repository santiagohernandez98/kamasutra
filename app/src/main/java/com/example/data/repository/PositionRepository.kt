package com.example.data.repository

import com.example.data.KamaPosition
import com.example.data.KamaPositionProvider
import com.example.data.db.PositionProgressDao
import com.example.data.db.PositionProgressEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class UserKamaPosition(
    val position: KamaPosition,
    val isFavorite: Boolean = false,
    val status: String = "UNEXPLORED", // "UNEXPLORED", "PLANNED", "COMPLETED"
    val rating: Int = 0,
    val notes: String = ""
)

class PositionRepository(private val progressDao: PositionProgressDao) {

    val allUserPositions: Flow<List<UserKamaPosition>> = progressDao.getAllProgress().map { progressList ->
        val progressMap = progressList.associateBy { it.positionId }
        KamaPositionProvider.positions.map { pos ->
            val progress = progressMap[pos.id]
            UserKamaPosition(
                position = pos,
                isFavorite = progress?.isFavorite ?: false,
                status = progress?.status ?: "UNEXPLORED",
                rating = progress?.rating ?: 0,
                notes = progress?.notes ?: ""
            )
        }
    }

    fun getUserPosition(positionId: String): Flow<UserKamaPosition?> {
        return progressDao.getProgressForPosition(positionId).map { progress ->
            val pos = KamaPositionProvider.positions.find { it.id == positionId } ?: return@map null
            UserKamaPosition(
                position = pos,
                isFavorite = progress?.isFavorite ?: false,
                status = progress?.status ?: "UNEXPLORED",
                rating = progress?.rating ?: 0,
                notes = progress?.notes ?: ""
            )
        }
    }

    suspend fun toggleFavorite(positionId: String) {
        val currentProgress = progressDao.getProgressForPositionSync(positionId)
        if (currentProgress == null) {
            progressDao.insertOrUpdateProgress(
                PositionProgressEntity(positionId = positionId, isFavorite = true)
            )
        } else {
            progressDao.updateFavorite(positionId, !currentProgress.isFavorite)
        }
    }

    suspend fun updateStatus(positionId: String, status: String) {
        val currentProgress = progressDao.getProgressForPositionSync(positionId)
        if (currentProgress == null) {
            progressDao.insertOrUpdateProgress(
                PositionProgressEntity(positionId = positionId, status = status)
            )
        } else {
            progressDao.updateStatus(positionId, status)
        }
    }

    suspend fun saveNotesAndRating(positionId: String, notes: String, rating: Int) {
        val currentProgress = progressDao.getProgressForPositionSync(positionId)
        if (currentProgress == null) {
            progressDao.insertOrUpdateProgress(
                PositionProgressEntity(positionId = positionId, notes = notes, rating = rating)
            )
        } else {
            progressDao.updateNotesAndRating(positionId, notes, rating)
        }
    }
}
