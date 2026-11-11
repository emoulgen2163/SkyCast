package com.emoulgen.skycast.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("cities")
data class CityEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val region: String,
    val isFavorite: Boolean = false,
    val lastOpenedAt: Long = System.currentTimeMillis()
)
