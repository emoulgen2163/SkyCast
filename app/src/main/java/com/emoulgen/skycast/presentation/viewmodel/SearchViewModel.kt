package com.emoulgen.skycast.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emoulgen.skycast.domain.repository.CityRepository
import com.emoulgen.skycast.presentation.viewmodel.SearchResult.Empty
import com.emoulgen.skycast.presentation.viewmodel.SearchResult.Error
import com.emoulgen.skycast.presentation.viewmodel.SearchResult.Loading
import com.emoulgen.skycast.presentation.viewmodel.SearchResult.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: CityRepository): ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())

    val uiState = _uiState.asStateFlow()



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

                val results = repository.searchCities(name = query)

                if (results.isEmpty()) {
                    _uiState.update {
                        it.copy(
                            results = Empty
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            results = Success(results)
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