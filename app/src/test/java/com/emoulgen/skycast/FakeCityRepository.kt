package com.emoulgen.skycast

import com.emoulgen.skycast.domain.model.City
import com.emoulgen.skycast.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.io.IOException

class FakeCityRepository: CityRepository {
    var citiesToReturn: List<City> = emptyList()
    var shouldThrow: Boolean = false

    override suspend fun searchCities(name: String): List<City> {
        if (shouldThrow) throw IOException()

        return citiesToReturn
    }

    override suspend fun saveLastOpened(city: City) {
    }

    override suspend fun getLastOpenedCity(): City? = null

    override suspend fun setFavorite(id: Int, isFavorite: Boolean) {
    }

    override fun isFavorite(id: Int): Flow<Boolean> = flowOf(false)

    override fun getFavorites(): Flow<List<City>> = flowOf(emptyList())

}