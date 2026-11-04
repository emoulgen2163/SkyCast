package com.emoulgen.skycast.presentation.viewmodel

import com.emoulgen.skycast.data.remote.dto.Result

sealed interface SearchResult {

    data object Idle: SearchResult
    data object Loading: SearchResult
    data class Success(val cities: List<Result>): SearchResult
    data object Empty: SearchResult
    data class Error(val errorMessage: String): SearchResult
}