package com.emoulgen.skycast.domain.repository

import com.emoulgen.skycast.domain.model.City

interface CityRepository {

    suspend fun searchCities(name: String): List<City>
}