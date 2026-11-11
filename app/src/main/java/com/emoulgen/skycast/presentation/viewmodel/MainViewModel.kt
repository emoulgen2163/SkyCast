package com.emoulgen.skycast.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emoulgen.skycast.domain.repository.CityRepository
import com.emoulgen.skycast.presentation.navigation.CityDetail
import com.emoulgen.skycast.presentation.navigation.SearchCity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(repository: CityRepository): ViewModel() {

    private val _startDestination = MutableStateFlow<Any?>(null)
    val startDestination = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            val city = repository.getLastOpenedCity()

            _startDestination.value = if(city != null) CityDetail(
                id = city.id,
                name = city.name,
                latitude = city.latitude,
                longitude = city.longitude,
                country = city.country,
                region = city.region,
            ) else SearchCity
        }
    }
}