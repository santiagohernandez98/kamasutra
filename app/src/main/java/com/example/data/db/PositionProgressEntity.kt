package com.example.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "position_progress")
data class PositionProgressEntity(
    @PrimaryKey val positionId: String,
    val isFavorite: Boolean = false,
    val status: String = "UNEXPLORED", // "UNEXPLORED", "PLANNED", "COMPLETED"
    val rating: Int = 0, // 0 to 5 stars
    val notes: String = "",
    val lastUpdated: Long = System.currentTimeMillis()
)
