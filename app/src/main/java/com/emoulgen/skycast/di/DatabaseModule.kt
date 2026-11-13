package com.emoulgen.skycast.di

import android.content.Context
import androidx.room.Room
import com.emoulgen.skycast.data.local.CityDao
import com.emoulgen.skycast.data.local.ForecastDao
import com.emoulgen.skycast.data.local.SkyCastDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SkyCastDatabase =
        Room.databaseBuilder(context, SkyCastDatabase::class.java, "skycast_db").fallbackToDestructiveMigration(dropAllTables = true).build()

    @Provides
    @Singleton
    fun provideCityDao(database: SkyCastDatabase): CityDao = database.cityDao()

    @Provides
    @Singleton
    fun provideForecastDao(database: SkyCastDatabase): ForecastDao = database.forecastDao()
}