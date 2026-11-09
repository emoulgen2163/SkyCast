package com.emoulgen.skycast.data.remote

import com.emoulgen.skycast.data.remote.dto.GeocodingResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApi {

    @GET("v1/search")
    suspend fun searchCities(
        @Query("name")
        name: String,
        @Query("count")
        count: Int = 10,
        @Query("language")
        language: String = "en"
    ): GeocodingResponseDto
}