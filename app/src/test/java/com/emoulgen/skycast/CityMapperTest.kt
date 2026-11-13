package com.emoulgen.skycast

import com.emoulgen.skycast.data.mapper.CityMapper.toCity
import com.emoulgen.skycast.data.remote.dto.CityDto
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CityMapperTest {

    @Test
    fun `no coordinates returns null`(){
        val cityDto = CityDto(
            id = 0,
            name = "New York",
            country = "US",
            latitude = null,
            longitude = null,
        )

        assertEquals(null, cityDto.toCity())
    }

    @Test
    fun `no id returns null`(){
        val cityDto = CityDto(
            id = null,
            name = "New York",
            country = "US",
            latitude = 40.7,
            longitude = 74.0,
        )

        assertEquals(null, cityDto.toCity())
    }

    @Test
    fun `no admin1 returns empty string in region`(){
        val cityDto = CityDto(
            id = 0,
            name = "New York",
            country = "US",
            latitude = 40.7,
            longitude = 74.0,
            admin1 = null
        )

        assertEquals("", cityDto.toCity()?.region)
    }
}