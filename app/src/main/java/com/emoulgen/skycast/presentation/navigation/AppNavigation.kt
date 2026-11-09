package com.emoulgen.skycast.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emoulgen.skycast.presentation.screens.SearchScreen
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SearchCity) {
        composable<SearchCity> {
            SearchScreen {

            }
        }
    }
}

@Serializable
object SearchCity