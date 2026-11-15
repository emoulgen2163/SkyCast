package com.emoulgen.skycast.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emoulgen.skycast.presentation.screens.SearchScreen
import com.emoulgen.skycast.presentation.screens.WeatherScreen
import com.emoulgen.skycast.presentation.viewmodel.MainViewModel
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = hiltViewModel()

    val startDestination by viewModel.startDestination.collectAsStateWithLifecycle()

    val start = startDestination

    if (start == null){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
        return
    }

    NavHost(navController = navController, startDestination = start) {
        composable<SearchCity> {
            SearchScreen(
                onCityClick = { city ->
                    navController.navigate(
                        CityDetail(
                            id = city.id,
                            name = city.name,
                            latitude = city.latitude,
                            longitude = city.longitude,
                            country = city.country,
                            region = city.region,
                        )
                    ){
                        popUpTo(navController.graph.id){
                            inclusive = true
                        }
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                },
                showBackButton = navController.previousBackStackEntry != null
            )
        }

        composable<CityDetail> {
            WeatherScreen {
                navController.navigate(SearchCity)
            }
        }
    }
}

@Serializable
object SearchCity

@Serializable
data class CityDetail(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val country: String,
    val region: String
)