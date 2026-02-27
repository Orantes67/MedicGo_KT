package com.TiololCode.medicgo.features.login.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.TiololCode.medicgo.core.navigation.DoctorDashboard
import com.TiololCode.medicgo.core.navigation.Login
import com.TiololCode.medicgo.core.navigation.Register
import com.TiololCode.medicgo.core.navigation.RouteRegistrar
import com.TiololCode.medicgo.features.login.presentation.screens.LoginScreen
import javax.inject.Inject

/**
 * Registers the Login route into the NavHost.
 * Owns the navigation actions that LoginScreen needs —
 * the screen itself stays unaware of route objects.
 */
class LoginRouteRegistrar @Inject constructor() : RouteRegistrar {

    override fun NavGraphBuilder.register(navController: NavHostController) {
        composable<Login> {
            LoginScreen(
                onNavigateToRegister = { navController.navigate(Register) },
                onLoginSuccess = {
                    navController.navigate(DoctorDashboard) {
                        popUpTo(Login) { inclusive = true }
                    }
                }
            )
        }
    }
}