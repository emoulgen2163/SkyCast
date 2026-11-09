package com.emoulgen.skycast.domain.model

import java.time.LocalDate

data class DailyForecast(
    val minTemperature: Double,
    val maxTemperature: Double,
    val time: LocalDate,
    val condition: WeatherCondition
)
