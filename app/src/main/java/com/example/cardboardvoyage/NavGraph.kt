package com.example.cardboardvoyage


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.cardboardvoyage.screens.HomeScreen
import com.example.cardboardvoyage.screens.StoryScreen
import kotlinx.serialization.Serializable

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeScreen
    ) {
        composable<HomeScreen> {
            HomeScreen(navController)
        }

        composable<StoryScreen> { backStackEntry ->
            val args = backStackEntry.toRoute<StoryScreen>()
            StoryScreen(navController, args)
        }



    }
}

@Serializable
object HomeScreen

@Serializable
data class StoryScreen (
    val id: Int?
)
