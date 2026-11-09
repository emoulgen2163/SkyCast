package com.emoulgen.skycast.domain.model

enum class WeatherCondition {
    Clear,
    Cloudy,
    Foggy,
    Drizzling,
    Rainy,
    Snowy,
    Stormy,
    Unknown;

    companion object {
        fun fromCode(code: Int): WeatherCondition{
            return when(code){
                0 -> Clear
                1, 2, 3 -> Cloudy
                45, 48 -> Foggy
                in 51..57 -> Drizzling
                in 61..67, in 80..82 -> Rainy
                in 71..77, in 85..86 -> Snowy
                in 95..99 -> Stormy
                else -> Unknown
            }
        }
    }
}