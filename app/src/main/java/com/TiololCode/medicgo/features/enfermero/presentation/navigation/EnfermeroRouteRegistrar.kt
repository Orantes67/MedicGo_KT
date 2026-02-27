package com.TiololCode.medicgo.features.enfermero.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.TiololCode.medicgo.core.navigation.EnfermeroDashboard
import com.TiololCode.medicgo.core.navigation.Login
import com.TiololCode.medicgo.core.navigation.RouteRegistrar
import com.TiololCode.medicgo.features.enfermero.presentation.screens.dashboard.EnfermeroMainScreen
import javax.inject.Inject

/**
 * Registers the EnfermeroDashboard route into the NavHost.
 */
class EnfermeroRouteRegistrar @Inject constructor() : RouteRegistrar {

    override fun NavGraphBuilder.register(navController: NavHostController) {
        composable<EnfermeroDashboard> {
            EnfermeroMainScreen(
                onLogout = {
                    navController.navigate(Login) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}
