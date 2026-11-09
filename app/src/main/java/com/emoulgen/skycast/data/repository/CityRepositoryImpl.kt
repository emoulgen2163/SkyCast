package com.emoulgen.skycast.data.repository

import com.emoulgen.skycast.data.remote.GeocodingApi
import com.emoulgen.skycast.data.remote.dto.Result
import com.emoulgen.skycast.domain.repository.CityRepository
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(private val api: GeocodingApi): CityRepository {
    override suspend fun searchCities(name: String): List<Result> {
        val response = api.searchCities(name)

        return response.results
    }
}