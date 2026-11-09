package com.emoulgen.skycast.presentation.viewmodel

import com.emoulgen.skycast.domain.model.City

sealed interface SearchResult {

    data object Idle: SearchResult
    data object Loading: SearchResult
    data class Success(val cities: List<City>): SearchResult
    data object Empty: SearchResult
    data class Error(val errorMessage: String): SearchResult
}