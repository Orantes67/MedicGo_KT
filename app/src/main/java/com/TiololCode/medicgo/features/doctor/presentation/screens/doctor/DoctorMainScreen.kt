package com.TiololCode.medicgo.features.doctor.presentation.screens.doctor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.DoctorViewModel
import com.TiololCode.medicgo.features.doctor.presentation.components.DoctorHeader
import com.TiololCode.medicgo.features.doctor.presentation.components.PatientDetailDialog
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorMetric
import com.TiololCode.medicgo.features.doctor.presentation.screens.doctor.data.sampleDoctorPatients

private val ScreenBackground = Color(0xFFF5F7FA)

@Composable
fun DoctorScreen(
    viewModel: DoctorViewModel = hiltViewModel(),
    onLogout: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.updateDoctorInfo("Dr. Carlos Ruiz", "Cardiología")
        viewModel.updatePatients(sampleDoctorPatients)
        viewModel.updateMetrics(
            DoctorMetric(
                totalPatients = 3,
                patientsUnderObservation = 1,
                criticalPatients = 1,
                stablePatients = 1
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ScreenBackground)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            DoctorHeader(
                doctorName = uiState.doctorName,
                specialty = uiState.doctorSpecialty,
                onLogoutClick = onLogout
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                DoctorContent(
                    uiState = uiState,
                    viewModel = viewModel
                )
            }
        }

        uiState.selectedPatient?.let { patient ->
            PatientDetailDialog(
                patient = patient,
                onDismiss = { viewModel.selectPatient(null) }
            )
        }
    }
}

