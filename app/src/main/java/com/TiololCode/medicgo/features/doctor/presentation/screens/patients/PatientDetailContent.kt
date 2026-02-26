package com.TiololCode.medicgo.features.doctor.presentation.screens.patients

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient
import com.TiololCode.medicgo.features.doctor.domain.entities.PatientNote
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.PatientDetailUiState
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.PatientDetailViewModel
import com.TiololCode.medicgo.features.doctor.presentation.screens.patients.data.samplePatientCritical
import com.TiololCode.medicgo.features.doctor.presentation.screens.patients.data.samplePatientObservation
import com.TiololCode.medicgo.features.doctor.presentation.screens.patients.data.samplePatientStable
import com.TiololCode.medicgo.features.doctor.presentation.screens.patients.data.samplePatientNotes
import com.TiololCode.medicgo.features.doctor.presentation.screens.patients.sections.PatientDetailHeader
import com.TiololCode.medicgo.features.doctor.presentation.screens.patients.sections.PatientBasicInfo
import com.TiololCode.medicgo.features.doctor.presentation.screens.patients.sections.NurseAssignmentSection
import com.TiololCode.medicgo.features.doctor.presentation.screens.patients.sections.PatientNotesSection
import com.TiololCode.medicgo.features.doctor.presentation.screens.patients.sections.StateUpdateSection

private val ScreenBackground = Color(0xFFF5F7FA)

@Composable
fun PatientDetailContent(
    patient: DoctorPatient,
    uiState: PatientDetailUiState,
    viewModel: PatientDetailViewModel,
    onBackClick: () -> Unit
) {
    PatientDetailContentStateless(
        patient = patient,
        notes = uiState.patientNotes,
        newNoteContent = uiState.newNoteContent,
        newPatientState = uiState.newPatientState,
        onNoteContentChange = { viewModel.updateNoteContent(it) },
        onAddNoteClick = { viewModel.setAddingNoteState(!uiState.isAddingNote) },
        onStateChange = { viewModel.updatePatientState(it) },
        onUpdateClick = { viewModel.setUpdatingStateStatus(!uiState.isUpdatingState) },
        onBackClick = onBackClick
    )
}

@Composable
private fun PatientDetailContentStateless(
    patient: DoctorPatient,
    notes: List<PatientNote>,
    newNoteContent: String,
    newPatientState: String,
    onNoteContentChange: (String) -> Unit,
    onAddNoteClick: () -> Unit,
    onStateChange: (String) -> Unit,
    onUpdateClick: () -> Unit,
    onBackClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(ScreenBackground),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { PatientDetailHeader(onBackClick = onBackClick) }
        item { PatientBasicInfo(patient = patient) }
        item { NurseAssignmentSection(patient = patient) }
        item {
            PatientNotesSection(
                notes = notes,
                newNoteContent = newNoteContent,
                onNoteContentChange = onNoteContentChange,
                onAddNoteClick = onAddNoteClick
            )
        }
        item {
            StateUpdateSection(
                currentState = patient.currentState,
                newState = newPatientState,
                onStateChange = onStateChange,
                onUpdateClick = onUpdateClick
            )
        }
        item { Spacer(modifier = Modifier.height(24.dp)) }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Paciente Crítico")
@Composable
private fun PreviewPatientDetailCritical() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackground)
    ) {
        PatientDetailContentStateless(
            patient = samplePatientCritical,
            notes = samplePatientNotes,
            newNoteContent = "",
            newPatientState = "",
            onNoteContentChange = {},
            onAddNoteClick = {},
            onStateChange = {},
            onUpdateClick = {},
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Paciente Observación")
@Composable
private fun PreviewPatientDetailObservation() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackground)
    ) {
        PatientDetailContentStateless(
            patient = samplePatientObservation,
            notes = emptyList(),
            newNoteContent = "Revisar temperatura cada 2 horas",
            newPatientState = "",
            onNoteContentChange = {},
            onAddNoteClick = {},
            onStateChange = {},
            onUpdateClick = {},
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Paciente Estable")
@Composable
private fun PreviewPatientDetailStable() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackground)
    ) {
        PatientDetailContentStateless(
            patient = samplePatientStable,
            notes = samplePatientNotes,
            newNoteContent = "",
            newPatientState = "Dado de alta",
            onNoteContentChange = {},
            onAddNoteClick = {},
            onStateChange = {},
            onUpdateClick = {},
            onBackClick = {}
        )
    }
}

