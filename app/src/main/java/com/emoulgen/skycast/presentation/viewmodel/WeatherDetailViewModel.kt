package com.emoulgen.skycast.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.emoulgen.skycast.domain.model.City
import com.emoulgen.skycast.domain.repository.CityRepository
import com.emoulgen.skycast.domain.repository.ForecastRepository
import com.emoulgen.skycast.presentation.navigation.CityDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: ForecastRepository,
    private val cityRepository: CityRepository
): ViewModel() {

    private val route = savedStateHandle.toRoute<CityDetail>()

    val cityName = route.name

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    init {
        loadForecast()

        viewModelScope.launch {

            val city = City(
                id = route.id,
                name = route.name,
                country = route.country,
                latitude = route.latitude,
                longitude = route.longitude,
                region = route.region
            )

            cityRepository.saveLastOpened(city)

            cityRepository.isFavorite(route.id).collect {
                _isFavorite.value = it
            }
        }
    }

    fun loadForecast() {

        viewModelScope.launch {

            _uiState.value = WeatherUiState.Loading

            try {
                val response = repository.getForecast(
                    latitude = route.latitude,
                    longitude = route.longitude,
                    cityId = route.id
                )
                _uiState.value = WeatherUiState.Success(response)
            } catch (e: IOException){
                _uiState.value = WeatherUiState.Error("Check your internet connection")
            } catch (e: HttpException){
                _uiState.value = WeatherUiState.Error("Could not access to the server. Try again later")
            } catch (e: Exception){
                _uiState.value = WeatherUiState.Error("An error occurred: ${e.localizedMessage}")
            }
        }
    }

    fun onToggleFavorite(){
        viewModelScope.launch {
            cityRepository.setFavorite(route.id, !_isFavorite.value)
        }
    }
}