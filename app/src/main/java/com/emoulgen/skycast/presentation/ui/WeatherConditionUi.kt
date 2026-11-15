package com.emoulgen.skycast.presentation.ui

import androidx.compose.ui.graphics.Color
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

fun WeatherCondition.gradientColors(): List<Color>{
    return when(this){
        WeatherCondition.Clear -> listOf(Color(0xFF4FACFE), Color(0xFF00C6FB))
        WeatherCondition.Rainy -> listOf(Color(0xFF4B6CB7), Color(0xFF182848))
        WeatherCondition.Stormy -> listOf(Color(0xFF232526), Color(0xFF414345))
        WeatherCondition.Cloudy -> listOf(Color(0xFFBBCDD2), Color(0xFF414345))
        WeatherCondition.Foggy -> listOf(Color(0xFFBBCDD2), Color(0xFF414345))
        WeatherCondition.Drizzling -> listOf(Color(0xFF4B6CB7), Color(0xFF4FACFE))
        WeatherCondition.Snowy -> listOf(Color(0xFFBBCDD2), Color(0xFF414345))
        WeatherCondition.Unknown -> emptyList()
    }
}

