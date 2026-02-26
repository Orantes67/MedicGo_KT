package com.TiololCode.medicgo.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

/**
 * Central NavHost. Knows nothing about individual screens.
 * Each feature registers its own routes via [RouteRegistrar],
 * provided through Hilt @IntoSet from each feature's data/di layer.
 */
@Composable
fun NavigationWrapper(registrars: Set<RouteRegistrar>) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Login
    ) {
        registrars.forEach { registrar ->
            with(registrar) { register(navController) }
        }
    }
}
