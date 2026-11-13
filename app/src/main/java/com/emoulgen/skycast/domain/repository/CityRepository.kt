package com.emoulgen.skycast.domain.repository

import com.emoulgen.skycast.domain.model.City
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    suspend fun searchCities(name: String): List<City>

    suspend fun saveLastOpened(city: City)

    suspend fun getLastOpenedCity(): City?

    suspend fun setFavorite(id: Int, isFavorite: Boolean)
    fun isFavorite(id: Int): Flow<Boolean>
    fun getFavorites(): Flow<List<City>>

}