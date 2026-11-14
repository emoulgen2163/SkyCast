package com.emoulgen.skycast.presentation.ui

import com.emoulgen.skycast.R
import com.emoulgen.skycast.domain.model.WeatherCondition

fun WeatherCondition.iconRes(): Int{
    return when(this){
        WeatherCondition.Clear -> R.drawable.baseline_wb_sunny_24
        WeatherCondition.Cloudy -> R.drawable.baseline_wb_cloudy_24
        WeatherCondition.Foggy -> R.drawable.baseline_foggy_24
        WeatherCondition.Drizzling -> R.drawable.baseline_water_drop_24
        WeatherCondition.Rainy -> R.drawable.outline_rainy_24
        WeatherCondition.Snowy -> R.drawable.outline_grain_24
        WeatherCondition.Stormy -> R.drawable.baseline_thunderstorm_24
        WeatherCondition.Unknown -> R.drawable.baseline_question_mark_24
    }
}

fun WeatherCondition.label(): String{
    return when(this){
        WeatherCondition.Clear -> "Clear / Sunny"
        WeatherCondition.Cloudy -> "Cloudy"
        WeatherCondition.Foggy -> "Foggy"
        WeatherCondition.Drizzling -> "Drizzling"
        WeatherCondition.Rainy -> "Rainy"
        WeatherCondition.Snowy -> "Snowy"
        WeatherCondition.Stormy -> "Stormy"
        WeatherCondition.Unknown -> "Unknown"
    }
}

