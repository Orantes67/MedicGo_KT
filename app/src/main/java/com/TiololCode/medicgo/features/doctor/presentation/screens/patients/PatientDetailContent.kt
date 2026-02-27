package com.TiololCode.medicgo.features.doctor.presentation.screens.patients

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.PatientDetailUiState
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.PatientDetailViewModel
import com.TiololCode.medicgo.features.doctor.presentation.screens.patients.sections.PatientDetailHeader
import com.TiololCode.medicgo.features.doctor.presentation.screens.patients.sections.PatientBasicInfo
import com.TiololCode.medicgo.features.doctor.presentation.screens.patients.sections.NurseAssignmentSection
import com.TiololCode.medicgo.features.doctor.presentation.screens.patients.sections.PatientNotesSection
import com.TiololCode.medicgo.features.doctor.presentation.screens.patients.sections.StateUpdateSection

private val ScreenBackground = Color(0xFFF5F7FA)

@Composable
fun PatientDetailContent(
    uiState: PatientDetailUiState,
    viewModel: PatientDetailViewModel,
    onBackClick: () -> Unit
) {
    val detail = uiState.detail ?: return

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(ScreenBackground),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { PatientDetailHeader(onBackClick = onBackClick) }
        item { PatientBasicInfo(detail = detail) }
        item { NurseAssignmentSection(detail = detail) }
        item {
            PatientNotesSection(
                notes = detail.clinicalNotes,
                newNoteContent = uiState.newNoteContent,
                isAddingNote = uiState.isAddingNote,
                onNoteContentChange = viewModel::onNoteContentChange,
                onAddNoteClick = viewModel::submitNote
            )
        }
        item {
            StateUpdateSection(
                currentState = detail.currentState,
                selectedState = uiState.selectedState,
                onStateSelect = viewModel::onStateSelect,
                onConfirmClick = viewModel::confirmStateUpdate,
                isUpdatingState = uiState.isUpdatingState
            )
        }
        item { Spacer(modifier = Modifier.height(24.dp)) }
    }
}

