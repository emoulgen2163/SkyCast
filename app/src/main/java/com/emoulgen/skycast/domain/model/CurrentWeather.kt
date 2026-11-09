package com.emoulgen.skycast.domain.model

import java.time.LocalDateTime

data class CurrentWeather(
    val humidity: Int,
    val temperature: Double,
    val time: LocalDateTime,
    val condition: WeatherCondition,
    val windSpeed: Double
)