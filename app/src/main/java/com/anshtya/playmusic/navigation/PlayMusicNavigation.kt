package com.anshtya.playmusic.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anshtya.playmusic.screens.HomeRoute

@Composable
fun PlayMusicNavigation(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = PlayMusicDestinations.Home.route,
    ) {
        composable(route = PlayMusicDestinations.Home.route) {
            HomeRoute()
        }
    }
}