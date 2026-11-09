package com.emoulgen.skycast.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ForecastResponseDto(
    @SerialName("current")
    val current: Current?,
    @SerialName("daily")
    val daily: Daily?,
    @SerialName("hourly")
    val hourly: Hourly?,
)