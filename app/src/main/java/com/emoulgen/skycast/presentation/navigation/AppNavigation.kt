package com.emoulgen.skycast.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emoulgen.skycast.presentation.screens.SearchScreen
import com.emoulgen.skycast.presentation.screens.WeatherScreen
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SearchCity) {
        composable<SearchCity> {
            SearchScreen(
                onCityClick = { city ->
                    navController.navigate(
                        CityDetail(
                            name = city.name,
                            latitude = city.latitude,
                            longitude = city.longitude
                        )
                    ){
                        popUpTo(navController.graph.id){
                            inclusive = true
                        }
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                }
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
    val name: String,
    val latitude: Double,
    val longitude: Double
)