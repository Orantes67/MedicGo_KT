package com.TiololCode.medicgo.features.doctor.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.TiololCode.medicgo.core.navigation.DoctorDashboard
import com.TiololCode.medicgo.core.navigation.Login
import com.TiololCode.medicgo.core.navigation.PatientDetail
import com.TiololCode.medicgo.core.navigation.RouteRegistrar
import com.TiololCode.medicgo.features.doctor.presentation.screens.doctor.DoctorScreen
import com.TiololCode.medicgo.features.doctor.presentation.screens.patients.PatientDetailScreen
import javax.inject.Inject

class DoctorRouteRegistrar @Inject constructor() : RouteRegistrar {

    override fun NavGraphBuilder.register(navController: NavHostController) {

        composable<DoctorDashboard> {
            DoctorScreen(
                onPatientClick = { patientId ->
                    navController.navigate(PatientDetail(patientId = patientId))
                },
                onLogout = {
                    navController.navigate(Login) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable<PatientDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<PatientDetail>()
            PatientDetailScreen(
                patientId   = route.patientId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
