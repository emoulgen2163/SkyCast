package com.emoulgen.skycast.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emoulgen.skycast.presentation.viewmodel.SearchResult.*
import com.emoulgen.skycast.utils.Retrofit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

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

    fun onSearch() {

        val query = _uiState.value.query
        if (query.isBlank()) return

        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    results = Loading
                )
            }

            try {

                val response = api.searchCities(name = query)

                if (response.results.isEmpty()) {
                    _uiState.update {
                        it.copy(
                            results = Empty
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            results = Success(response.results)
                        )
                    }
                }
            } catch (e: IOException) {
                _uiState.update {
                    it.copy(
                        results = Error("Check your internet connection")
                    )
                }
            } catch (e: HttpException) {
                _uiState.update {
                    it.copy(
                        results = Error("Could not access to the server. Try again later")
                    )
                }
            } catch (e: Exception){
                _uiState.update {
                    it.copy(
                        results = Error("${e.localizedMessage}")
                    )
                }
            }

        }
    }
}