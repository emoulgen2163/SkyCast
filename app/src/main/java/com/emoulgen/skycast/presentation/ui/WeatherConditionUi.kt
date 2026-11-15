package com.emoulgen.skycast.presentation.ui

import androidx.compose.ui.graphics.Color
import com.emoulgen.skycast.R
import com.emoulgen.skycast.domain.model.WeatherCondition
import com.emoulgen.skycast.presentation.ui.theme.ClearBottom
import com.emoulgen.skycast.presentation.ui.theme.ClearTop
import com.emoulgen.skycast.presentation.ui.theme.CloudyBottom
import com.emoulgen.skycast.presentation.ui.theme.CloudyTop
import com.emoulgen.skycast.presentation.ui.theme.DrizzlingBottom
import com.emoulgen.skycast.presentation.ui.theme.DrizzlingTop
import com.emoulgen.skycast.presentation.ui.theme.FoggyBottom
import com.emoulgen.skycast.presentation.ui.theme.FoggyTop
import com.emoulgen.skycast.presentation.ui.theme.RainyBottom
import com.emoulgen.skycast.presentation.ui.theme.RainyTop
import com.emoulgen.skycast.presentation.ui.theme.SnowyBottom
import com.emoulgen.skycast.presentation.ui.theme.SnowyTop
import com.emoulgen.skycast.presentation.ui.theme.StormyBottom
import com.emoulgen.skycast.presentation.ui.theme.StormyTop
import com.emoulgen.skycast.presentation.ui.theme.UnknownBottom
import com.emoulgen.skycast.presentation.ui.theme.UnknownTop

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
        WeatherCondition.Clear -> listOf(ClearTop, ClearBottom)
        WeatherCondition.Rainy -> listOf(RainyTop, RainyBottom)
        WeatherCondition.Stormy -> listOf(StormyTop, StormyBottom)
        WeatherCondition.Cloudy -> listOf(CloudyTop, CloudyBottom)
        WeatherCondition.Foggy -> listOf(FoggyTop, FoggyBottom)
        WeatherCondition.Drizzling -> listOf(DrizzlingTop, DrizzlingBottom)
        WeatherCondition.Snowy -> listOf(SnowyTop, SnowyBottom)
        WeatherCondition.Unknown -> listOf(UnknownTop, UnknownBottom)
    }
}

