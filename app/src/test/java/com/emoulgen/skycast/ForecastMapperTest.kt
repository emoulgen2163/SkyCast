package com.emoulgen.skycast

import com.emoulgen.skycast.data.mapper.ForecastMapper.toHourlyForecasts
import com.emoulgen.skycast.data.remote.dto.Hourly
import org.junit.Assert.assertEquals
import org.junit.Test

class ForecastMapperTest {


    @Test
    fun `three hours of data gives three forecasts`(){
        val temperatures = listOf(
            22.0,
            24.0,
            26.0
        )

        val times = listOf(
            "2026-11-13T12:00",
            "2026-11-13T14:00",
            "2026-11-13T16:00"
        )

        val codes = listOf(
            0,
            0,
            2
        )

        val hourly = Hourly(
            temperature2m = temperatures,
            time = times,
            weatherCode = codes
        )

        assertEquals(3, hourly.toHourlyForecasts().size)
    }

    @Test
    fun `null value should be skipped`(){
        val temperatures = listOf(
            22.0,
            null,
            26.0
        )

        val times = listOf(
            "2026-11-13T12:00",
            "2026-11-13T14:00",
            "2026-11-13T16:00"
        )

        val codes = listOf(
            0,
            0,
            2
        )

        val hourly = Hourly(
            temperature2m = temperatures,
            time = times,
            weatherCode = codes
        )

        assertEquals(2, hourly.toHourlyForecasts().size)
    }
}