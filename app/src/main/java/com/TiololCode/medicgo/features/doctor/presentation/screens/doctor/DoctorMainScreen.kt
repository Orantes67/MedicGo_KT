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
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient

private val ScreenBackground = Color(0xFFF5F7FA)

// Sample data
private val samplePatients = listOf(
    DoctorPatient(
        id = 1L,
        name = "Roberto",
        lastName = "Torres",
        age = 54,
        bloodType = "O+",
        symptoms = "Dolor en el pecho, dificultad para respirar, presión arterial elevada",
        currentState = "Crítico",
        conditionNote = "Requiere monitoreo constante",
        registrationDate = "2024-11-15",
        areaName = "Urgencias",
        assignedNurseName = "Ana García",
        assignedNurseId = 2L,
        lastUpdateTime = "2024-11-20 14:30",
        areaId = 1L
    ),
    DoctorPatient(
        id = 2L,
        name = "Laura",
        lastName = "Sánchez",
        age = 38,
        bloodType = "A-",
        symptoms = "Fiebre persistente, fatiga, dolor de cabeza moderado",
        currentState = "En observación",
        conditionNote = "Pendiente de resultados de laboratorio",
        registrationDate = "2024-11-14",
        areaName = "UCI",
        assignedNurseName = "Carlos Mendoza",
        assignedNurseId = 3L,
        lastUpdateTime = "2024-11-20 13:15",
        areaId = 2L
    ),
    DoctorPatient(
        id = 3L,
        name = "María",
        lastName = "González",
        age = 45,
        bloodType = "B+",
        symptoms = "Recuperación post-operatoria, sin complicaciones reportadas",
        currentState = "Estable",
        conditionNote = "Procedimiento exitoso, observar durante 48 horas",
        registrationDate = "2024-11-10",
        areaName = "Hospitalización",
        assignedNurseName = "Ana García",
        assignedNurseId = 2L,
        lastUpdateTime = "2024-11-20 10:45",
        areaId = 3L
    )
)

@Composable
fun DoctorScreen(
    viewModel: DoctorViewModel = hiltViewModel(),
    onLogout: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.updateDoctorInfo("Dr. Carlos Ruiz", "Cardiología")
        viewModel.updatePatients(samplePatients)
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

        // Show patient detail dialog when a patient is selected
        uiState.selectedPatient?.let { patient ->
            PatientDetailDialog(
                patient = patient,
                onDismiss = { viewModel.selectPatient(null) }
            )
        }
    }
}

