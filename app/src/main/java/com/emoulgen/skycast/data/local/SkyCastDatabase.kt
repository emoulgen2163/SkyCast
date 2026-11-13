package com.emoulgen.skycast.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CityEntity::class, ForecastCacheEntity::class], version = 2, exportSchema = false)
abstract class SkyCastDatabase: RoomDatabase() {

    abstract fun cityDao(): CityDao

    abstract fun forecastDao(): ForecastDao
}