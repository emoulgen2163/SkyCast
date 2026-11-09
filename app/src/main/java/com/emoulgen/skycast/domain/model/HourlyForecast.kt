package com.emoulgen.skycast.domain.model

import java.time.LocalDateTime

data class HourlyForecast(
    val temperature: Double,
    val time: LocalDateTime,
    val condition: WeatherCondition
)
