package com.emoulgen.skycast.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Weather(
    @SerialName("results")
    val results: List<Result> = emptyList()
)