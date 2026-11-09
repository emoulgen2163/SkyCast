package com.emoulgen.skycast.utils

import com.emoulgen.skycast.data.remote.GeocodingApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object Constants {

    const val BASE_URL = "https://geocoding-api.open-meteo.com/"
}