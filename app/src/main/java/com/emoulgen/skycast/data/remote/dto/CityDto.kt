package com.emoulgen.skycast.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CityDto(
    @SerialName("admin1")
    val admin1: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("latitude")
    val latitude: Double? = null,
    @SerialName("longitude")
    val longitude: Double? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("population")
    val population: Int? = null
)