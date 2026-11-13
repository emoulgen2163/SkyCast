package com.emoulgen.skycast.domain.model

import java.time.LocalDateTime

data class Forecast(
    val current: CurrentWeather,
    val hourly: List<HourlyForecast>,
    val daily: List<DailyForecast>,
    val updatedAt: LocalDateTime,
    val isFromCache: Boolean
)
