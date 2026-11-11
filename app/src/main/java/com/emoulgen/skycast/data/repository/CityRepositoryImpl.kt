package com.emoulgen.skycast.data.repository

import com.emoulgen.skycast.data.local.CityDao
import com.emoulgen.skycast.data.mapper.CityMapper.toCity
import com.emoulgen.skycast.data.mapper.CityMapper.toCityEntity
import com.emoulgen.skycast.data.remote.GeocodingApi
import com.emoulgen.skycast.domain.model.City
import com.emoulgen.skycast.domain.repository.CityRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(private val api: GeocodingApi, private val dao: CityDao): CityRepository {
    override suspend fun searchCities(name: String): List<City> {
        val response = api.searchCities(name)

        return response.results.mapNotNull {
            it.toCity()
        }
    }

    override suspend fun saveLastOpened(city: City) {
        val existing = dao.getCity(city.id).first()

        dao.upsertCity(
            city.toCityEntity(
                isFavorite = existing?.isFavorite ?: false,
                lastOpenedAt = System.currentTimeMillis()
            )
        )
    }

    override suspend fun getLastOpenedCity(): City? = dao.getLastOpenedCity().first()?.toCity()
}