package com.emoulgen.skycast.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emoulgen.skycast.utils.Retrofit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())

    val uiState = _uiState.asStateFlow()

    private val api = Retrofit.geocodingApi

    fun onQueryChange(text: String){
        _uiState.update {
            it.copy(
               query = text
            )
        }
    }

    fun onSearch(){
        viewModelScope.launch {
            try {
                if (_uiState.value.query.isNotBlank()) {
                    val response = api.searchCities(name = _uiState.value.query)
                    _uiState.update {
                        it.copy(
                            results = response.results
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", "${e.localizedMessage}")
            }
        }
    }
}