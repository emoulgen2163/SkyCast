package com.emoulgen.skycast.data.repository

import com.emoulgen.skycast.data.mapper.ForecastMapper.toForecast
import com.emoulgen.skycast.data.remote.ForecastApi
import com.emoulgen.skycast.domain.model.Forecast
import com.emoulgen.skycast.domain.repository.ForecastRepository
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(private val api: ForecastApi): ForecastRepository {
    override suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ): Forecast {
        val response = api.getForecast(latitude, longitude)

        return response.toForecast()
    }
}