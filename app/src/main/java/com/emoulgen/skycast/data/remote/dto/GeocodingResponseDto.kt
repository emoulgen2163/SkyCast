package com.emoulgen.skycast.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GeocodingResponseDto(
    @SerialName("results")
    val results: List<CityDto> = emptyList()
)