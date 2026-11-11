package com.emoulgen.skycast.presentation.viewmodel

import com.emoulgen.skycast.domain.model.Forecast

sealed interface WeatherUiState {
    data object Loading: WeatherUiState
    data class Success(val forecast: Forecast): WeatherUiState
    data class Error(val message: String): WeatherUiState
}