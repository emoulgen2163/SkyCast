package com.emoulgen.skycast.fakeClasses

import com.emoulgen.skycast.data.remote.ForecastApi
import com.emoulgen.skycast.data.remote.dto.Current
import com.emoulgen.skycast.data.remote.dto.ForecastResponseDto
import java.io.IOException

class FakeForecastApi: ForecastApi {

    var shouldThrow = false
    override suspend fun getForecast(
        latitude: Double,
        longitude: Double,
        current: String,
        hourly: String,
        daily: String,
        timezone: String,
        forecastDays: Int
    ): ForecastResponseDto {
        if (shouldThrow) throw IOException()
        
        val dto = ForecastResponseDto(
            current = Current(
                interval = 4,
                relativeHumidity2m = 43,
                temperature2m = 22.0,
                time = "2026-11-14T12:00",
                weatherCode = 0,
                windSpeed10m = 31.0
            ),
            daily = null,
            hourly = null
        )

        return dto
    }
}