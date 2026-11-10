package com.emoulgen.skycast.domain.repository

import com.emoulgen.skycast.domain.model.Forecast


interface ForecastRepository {

    suspend fun getForecast(latitude: Double, longitude: Double): Forecast
}