package com.emoulgen.skycast.domain.repository

import com.emoulgen.skycast.data.remote.dto.Result

interface CityRepository {

    suspend fun searchCities(name: String): List<Result>
}