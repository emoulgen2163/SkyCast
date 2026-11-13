package com.emoulgen.skycast.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ForecastCacheEntity(
    @PrimaryKey
    val cityId: Int,
    val json: String,
    val updatedAt: Long
)
