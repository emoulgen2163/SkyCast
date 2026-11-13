package com.emoulgen.skycast.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ForecastDao {

    @Upsert
    suspend fun upsertCache(cache: ForecastCacheEntity)

    @Query("SELECT * FROM ForecastCacheEntity WHERE cityId = :id")
    suspend fun getCache(id: Int): ForecastCacheEntity?
}