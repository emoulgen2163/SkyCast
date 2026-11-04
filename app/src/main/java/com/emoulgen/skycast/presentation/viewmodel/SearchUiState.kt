package com.emoulgen.skycast.presentation.viewmodel

data class SearchUiState(
    val query: String = "",
    val results: SearchResult = SearchResult.Idle
)
