package com.emoulgen.skycast.data.repository

import com.emoulgen.skycast.data.local.ForecastCacheEntity
import com.emoulgen.skycast.data.local.ForecastDao
import com.emoulgen.skycast.data.mapper.ForecastMapper.toForecast
import com.emoulgen.skycast.data.remote.ForecastApi
import com.emoulgen.skycast.data.remote.dto.ForecastResponseDto
import com.emoulgen.skycast.domain.model.Forecast
import com.emoulgen.skycast.domain.repository.ForecastRepository
import kotlinx.serialization.json.Json
import java.io.IOException
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val api: ForecastApi,
    private val dao: ForecastDao,
    private val json: Json
): ForecastRepository {
    override suspend fun getForecast(
        cityId: Int,
        latitude: Double,
        longitude: Double
    ): Forecast {
        return try {
            val response = api.getForecast(latitude, longitude)

            val cache = ForecastCacheEntity(
                cityId = cityId,
                json = json.encodeToString(response),
                updatedAt = System.currentTimeMillis()
            )

            dao.upsertCache(cache)

            response.toForecast(
                updatedAt = LocalDateTime.now(),
                isFromCache = false
            )
        } catch (e: IOException) {
            val cache = dao.getCache(cityId) ?: throw e

            val dto = json.decodeFromString<ForecastResponseDto>(cache.json)

            dto.toForecast(
                updatedAt = Instant.ofEpochMilli(cache.updatedAt).atZone(ZoneId.systemDefault()).toLocalDateTime(),
                isFromCache = true
            )
        }
    }
}