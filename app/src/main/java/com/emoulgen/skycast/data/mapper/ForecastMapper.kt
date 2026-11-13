package com.emoulgen.skycast.data.mapper

import com.emoulgen.skycast.data.remote.dto.Current
import com.emoulgen.skycast.data.remote.dto.Daily
import com.emoulgen.skycast.data.remote.dto.ForecastResponseDto
import com.emoulgen.skycast.data.remote.dto.Hourly
import com.emoulgen.skycast.domain.model.CurrentWeather
import com.emoulgen.skycast.domain.model.DailyForecast
import com.emoulgen.skycast.domain.model.Forecast
import com.emoulgen.skycast.domain.model.HourlyForecast
import com.emoulgen.skycast.domain.model.WeatherCondition
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.collections.orEmpty

object ForecastMapper {
    fun Hourly.toHourlyForecasts(): List<HourlyForecast>{
        val times = time.orEmpty()
        val temperatures = temperature2m.orEmpty()
        val codes = weatherCode.orEmpty()

        return times.indices.mapNotNull { i ->

            val time = times.getOrNull(i) ?: return@mapNotNull null
            val temperature = temperatures.getOrNull(i) ?: return@mapNotNull null
            val code = codes.getOrNull(i) ?: return@mapNotNull null

            HourlyForecast(
                temperature = temperature,
                time = LocalDateTime.parse(time),
                condition = WeatherCondition.fromCode(code)
            )
        }
    }

    fun Daily.toDailyForecasts(): List<DailyForecast>{
        val maxTemperatures = temperature2mMax.orEmpty()
        val minTemperatures = temperature2mMin.orEmpty()
        val times = time.orEmpty()
        val codes = weatherCode.orEmpty()

        return times.indices.mapNotNull { i ->
            val time = times.getOrNull(i) ?: return@mapNotNull null
            val maxTemperature = maxTemperatures.getOrNull(i) ?: return@mapNotNull null
            val minTemperature = minTemperatures.getOrNull(i) ?: return@mapNotNull null
            val code = codes.getOrNull(i) ?: return@mapNotNull null

            DailyForecast(
                minTemperature = minTemperature,
                maxTemperature = maxTemperature,
                time = LocalDate.parse(time),
                condition = WeatherCondition.fromCode(code)
            )
        }
    }

    fun Current.toCurrentWeather(): CurrentWeather{
        return CurrentWeather(
            humidity = relativeHumidity2m ?: error("missing humidity"),
            temperature = temperature2m ?: error("missing temperature"),
            time = LocalDateTime.parse(time ?: error("missing time")),
            condition = WeatherCondition.fromCode(weatherCode ?: error("missing code")),
            windSpeed = windSpeed10m ?: error("missing wind speed")
        )
    }
    
    fun ForecastResponseDto.toForecast(updatedAt: LocalDateTime, isFromCache: Boolean): Forecast{

        return Forecast(
            current = current?.toCurrentWeather() ?: error("no current information"),
            hourly = hourly?.toHourlyForecasts() ?: emptyList(),
            daily = daily?.toDailyForecasts() ?: emptyList(),
            updatedAt = updatedAt,
            isFromCache = isFromCache
        )
    }
}