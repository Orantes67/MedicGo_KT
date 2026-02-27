package com.TiololCode.medicgo.features.login.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.TiololCode.medicgo.core.navigation.DoctorDashboard
import com.TiololCode.medicgo.core.navigation.EnfermeroDashboard
import com.TiololCode.medicgo.core.navigation.Login
import com.TiololCode.medicgo.core.navigation.Register
import com.TiololCode.medicgo.core.navigation.RouteRegistrar
import com.TiololCode.medicgo.core.security.TokenManager
import com.TiololCode.medicgo.features.login.presentation.screens.LoginScreen
import javax.inject.Inject

/**
 * Registers the Login route into the NavHost.
 * Owns the navigation actions that LoginScreen needs —
 * the screen itself stays unaware of route objects.
 * Routes to EnfermeroDashboard or DoctorDashboard based on user role.
 */
class LoginRouteRegistrar @Inject constructor(
    private val tokenManager: TokenManager
) : RouteRegistrar {

    override fun NavGraphBuilder.register(navController: NavHostController) {
        composable<Login> {
            LoginScreen(
                onNavigateToRegister = { navController.navigate(Register) },
                onLoginSuccess = {
                    val destination = when (tokenManager.getUserRole()?.lowercase()) {
                        "enfermero", "enfermera", "nurse" -> EnfermeroDashboard
                        else -> DoctorDashboard
                    }
                    navController.navigate(destination) {
                        popUpTo(Login) { inclusive = true }
                    }
                }
            )
        }
    }
}