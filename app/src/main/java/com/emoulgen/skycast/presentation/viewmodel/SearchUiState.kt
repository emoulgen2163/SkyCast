package com.emoulgen.skycast.presentation.viewmodel

import com.emoulgen.skycast.data.remote.dto.Result

data class SearchUiState(
    val query: String = "",
    val results: List<Result> = emptyList()
)
