package com.TiololCode.medicgo.features.register.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.TiololCode.medicgo.core.navigation.Register
import com.TiololCode.medicgo.core.navigation.RouteRegistrar
import com.TiololCode.medicgo.features.register.presentation.screens.RegisterScreen
import javax.inject.Inject

/**
 * Registers the Register route into the NavHost.
 * Owns the navigation actions that RegisterScreen needs.
 */
class RegisterRouteRegistrar @Inject constructor() : RouteRegistrar {

    override fun NavGraphBuilder.register(navController: NavHostController) {
        composable<Register> {
            RegisterScreen(
                onNavigateBack = { navController.popBackStack() },
                onRegisterSuccess = { navController.popBackStack() },
                onNavigateToLogin = { navController.popBackStack() }
            )
        }
    }
}