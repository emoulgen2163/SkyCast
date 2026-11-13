package com.emoulgen.skycast

import com.emoulgen.skycast.domain.model.WeatherCondition
import junit.framework.TestCase.assertEquals
import org.junit.Test

class WeatherConditionTest {

    @Test
    fun `code 63 is rainy`(){
        assertEquals(WeatherCondition.Rainy, WeatherCondition.fromCode(63))
    }

    @Test
    fun `code 0 is clear`(){
        assertEquals(WeatherCondition.Clear, WeatherCondition.fromCode(0))
    }

    @Test
    fun `code 2 is cloudy`(){
        assertEquals(WeatherCondition.Cloudy, WeatherCondition.fromCode(2))
    }

    @Test
    fun `code 81 is rainy`(){
        assertEquals(WeatherCondition.Rainy, WeatherCondition.fromCode(81))
    }

    @Test
    fun `code 999 is unknown`(){
        assertEquals(WeatherCondition.Unknown, WeatherCondition.fromCode(999))
    }
}