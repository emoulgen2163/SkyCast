package com.emoulgen.skycast.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Current(
    @SerialName("interval")
    val interval: Int?,
    @SerialName("relative_humidity_2m")
    val relativeHumidity2m: Int?,
    @SerialName("temperature_2m")
    val temperature2m: Double?,
    @SerialName("time")
    val time: String?,
    @SerialName("weather_code")
    val weatherCode: Int?,
    @SerialName("wind_speed_10m")
    val windSpeed10m: Double?
)