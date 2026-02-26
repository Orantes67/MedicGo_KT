package com.TiololCode.medicgo.features.doctor.presentation.screens.doctor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.DoctorViewModel
import com.TiololCode.medicgo.features.doctor.data.datasource.DoctorMockData
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

@Composable
fun DoctorMainScreen(
    viewModel: DoctorViewModel = hiltViewModel(),
    onLogout: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        // Cargar datos de ejemplo
        viewModel.updateDoctorInfo("Dr. Carlos Mendoza", "Cardiología")
        viewModel.updateMetrics(DoctorMockData.getMockMetrics())
        viewModel.updatePatients(DoctorMockData.getMockPatients())
    }

    if (uiState.selectedPatient != null) {
        Surface(tonalElevation = 8.dp) {
            com.TiololCode.medicgo.features.doctor.presentation.screens.patients.PatientDetailScreen(
                patient = uiState.selectedPatient!!,
                onBackClick = { viewModel.selectPatient(null) }
            )
        }
    } else {
        DoctorScreen(
            viewModel = viewModel,
            onLogout = onLogout
        )
    }
}

