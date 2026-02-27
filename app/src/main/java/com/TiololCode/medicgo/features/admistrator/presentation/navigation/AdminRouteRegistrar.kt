package com.TiololCode.medicgo.features.admistrator.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.TiololCode.medicgo.core.navigation.AdminDashboard
import com.TiololCode.medicgo.core.navigation.Login
import com.TiololCode.medicgo.core.navigation.RouteRegistrar
import com.TiololCode.medicgo.features.admistrator.presentation.screens.administrator.AdministratorScreen
import javax.inject.Inject

class AdminRouteRegistrar @Inject constructor() : RouteRegistrar {

    override fun NavGraphBuilder.register(navController: NavHostController) {
        composable<AdminDashboard> {
            AdministratorScreen(
                onLogout = {
                    navController.navigate(Login) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}
