package com.emoulgen.skycast.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.emoulgen.skycast.domain.model.City
import com.emoulgen.skycast.domain.model.CurrentWeather
import com.emoulgen.skycast.domain.model.DailyForecast
import com.emoulgen.skycast.domain.model.Forecast
import com.emoulgen.skycast.domain.model.HourlyForecast
import com.emoulgen.skycast.domain.model.WeatherCondition
import com.emoulgen.skycast.presentation.viewmodel.WeatherDetailViewModel
import com.emoulgen.skycast.presentation.viewmodel.WeatherUiState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    viewModel: WeatherDetailViewModel = hiltViewModel(),
    onSearchClick: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isFavorite by viewModel.isFavorite.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SkyCast") },
                actions = {
                    IconButton(
                        onClick = onSearchClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }


                    IconButton(
                        onClick = { viewModel.onToggleFavorite() }
                    ) {

                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) Color(0xFFFFC107) else Color.Gray
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
        ) {
            WeatherContent(
                cityName = viewModel.cityName,
                uiState = uiState,
            ) {
                viewModel.loadForecast()
            }
        }
    }
}

@Composable
fun WeatherContent(
    cityName: String,
    uiState: WeatherUiState,
    onRetry: () -> Unit
) {

    when (uiState) {

        is WeatherUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }

        is WeatherUiState.Error -> {
            Box(
                modifier = Modifier.padding(16.dp)
            ){
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(uiState.message)

                    Spacer(Modifier.height(16.dp))

                    Button(
                        onClick = onRetry
                    ) {
                        Text("Try Again")
                    }
                }
            }
        }

        is WeatherUiState.Success -> {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Box(
                    modifier = Modifier.padding(16.dp)
                ){
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = cityName,
                            fontSize = 32.sp
                        )

                        Text("${uiState.forecast.current.condition}")

                        Text("${uiState.forecast.current.temperature}ºC")

                        Spacer(Modifier.height(16.dp))

                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            items(uiState.forecast.hourly){
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("${it.time.hour}.00 ")
                                    Text("${it.condition}")
                                    Text("${it.temperature} °C ")
                                }
                            }
                        }

                        Spacer(Modifier.height(16.dp))

                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            items(uiState.forecast.daily){
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("${it.time.dayOfWeek}")
                                    Text("${it.condition}")
                                    Text("${it.maxTemperature} °C ")
                                    Text("${it.minTemperature} °C ")
                                }
                            }
                        }

                        if (uiState.forecast.isFromCache) {
                            Spacer(Modifier.height(32.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                val time = uiState.forecast.updatedAt.format(DateTimeFormatter.ofPattern("HH:mm"))
                                Text("Last updated: $time" )
                            }
                        }
                    }
                }
            }
        }
    }


}

@Preview
@Composable
fun WeatherContentScreenPreview() {
    val sampleForecast = Forecast(
        current = CurrentWeather(
            humidity = 60,
            temperature = 12.5,
            time = LocalDateTime.of(2026, 11, 11, 14, 0),
            condition = WeatherCondition.Cloudy,
            windSpeed = 8.0
        ),
        hourly = List(6) { i ->
            HourlyForecast(
                temperature = 12.0 + i,
                time = LocalDateTime.of(2026, 11, 11, 14 + i, 0),
                condition = WeatherCondition.Clear
            )
        },
        daily = List(7) { i ->
            DailyForecast(
                minTemperature = 8.0 + i,
                maxTemperature = 12.0 + i,
                time = LocalDate.of(2026, 11, 11 + i),
                condition = WeatherCondition.Cloudy
            )
        },
        updatedAt = LocalDateTime.now(),
        isFromCache = true
    )
    WeatherContent(
        cityName = "New York",
        uiState = WeatherUiState.Success(sampleForecast)
    ) { }
}