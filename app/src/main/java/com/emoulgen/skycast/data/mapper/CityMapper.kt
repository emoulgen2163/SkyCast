package com.emoulgen.skycast.data.mapper

import com.emoulgen.skycast.data.local.CityEntity
import com.emoulgen.skycast.data.remote.dto.CityDto
import com.emoulgen.skycast.domain.model.City

object CityMapper {

    fun CityDto.toCity(): City?{
        if (id == null || latitude == null || longitude == null) return null

        return City(
            id = id ,
            name = name ?: "",
            country = country ?: "",
            latitude = latitude,
            longitude = longitude,
            region = admin1 ?: ""
        )
    }

    fun CityEntity.toCity(): City{
        return City(
            id = id,
            name = name,
            country = country,
            latitude = latitude,
            longitude = longitude,
            region = region,
        )
    }

    fun City.toCityEntity(isFavorite: Boolean, lastOpenedAt: Long): CityEntity{
        return CityEntity(
            id = id,
            name = name,
            country = country,
            latitude = latitude,
            longitude = longitude,
            region = region,
            isFavorite = isFavorite,
            lastOpenedAt = lastOpenedAt
        )
    }
}