package com.TiololCode.medicgo.features.doctor.presentation.screens.patients

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.PatientDetailUiState
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.PatientDetailViewModel

private val PrimaryText = Color(0xFF1A1A2E)
private val SecondaryText = Color(0xFF888888)
private val HeaderBackground = Color(0xFF1A1A2E)
private val HeaderText = Color(0xFFFFFFFF)
private val PrimaryColor = Color(0xFF42A5F5)
private val CardBackground = Color(0xFFFFFFFF)

@Composable
fun PatientDetailContent(
    patient: DoctorPatient,
    uiState: PatientDetailUiState,
    viewModel: PatientDetailViewModel,
    onBackClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item {
            PatientDetailHeader(onBackClick = onBackClick)
        }

        item {
            PatientBasicInfo(patient = patient)
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            NurseAssignmentSection(patient = patient)
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            PatientNotesSection(
                notes = uiState.patientNotes,
                newNoteContent = uiState.newNoteContent,
                onNoteContentChange = { viewModel.updateNoteContent(it) },
                onAddNoteClick = { viewModel.setAddingNoteState(!uiState.isAddingNote) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            StateUpdateSection(
                currentState = patient.currentState,
                newState = uiState.newPatientState,
                onStateChange = { viewModel.updatePatientState(it) },
                onUpdateClick = { viewModel.setUpdatingStateStatus(!uiState.isUpdatingState) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun PatientDetailHeader(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = HeaderBackground)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = PrimaryColor,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { onBackClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = HeaderText,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Detalles del Paciente",
                fontSize = 18.sp,
                color = HeaderText,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun PatientBasicInfo(patient: DoctorPatient) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = CardBackground)
            .padding(16.dp)
    ) {
        Column {
            InfoRow(label = "Nombre", value = "${patient.name} ${patient.lastName}")
            InfoRow(label = "Edad", value = "${patient.age} años")
            InfoRow(label = "Tipo de Sangre", value = patient.bloodType)
            InfoRow(label = "Área", value = patient.areaName)
            InfoRow(label = "Estado Actual", value = patient.currentState)
            InfoRow(label = "Síntomas", value = patient.symptoms)
            InfoRow(label = "Fecha de Registro", value = patient.registrationDate)
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = SecondaryText,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 14.sp,
            color = PrimaryText,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
private fun NurseAssignmentSection(patient: DoctorPatient) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = CardBackground)
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "Enfermera Asignada",
                fontSize = 14.sp,
                color = PrimaryText,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = patient.assignedNurseName,
                fontSize = 14.sp,
                color = PrimaryColor,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun PatientNotesSection(
    notes: List<com.TiololCode.medicgo.features.doctor.domain.entities.PatientNote>,
    newNoteContent: String,
    onNoteContentChange: (String) -> Unit,
    onAddNoteClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = CardBackground)
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "Notas del Paciente",
                fontSize = 14.sp,
                color = PrimaryText,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))

            notes.forEach { note ->
                NoteItem(note = note)
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = newNoteContent,
                onValueChange = onNoteContentChange,
                label = { Text("Agregar nueva nota") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onAddNoteClick,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar Nota")
            }
        }
    }
}

@Composable
private fun NoteItem(note: com.TiololCode.medicgo.features.doctor.domain.entities.PatientNote) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFEEEEEE),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp)
    ) {
        Column {
            Text(
                text = note.content,
                fontSize = 12.sp,
                color = PrimaryText
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = note.createdDate,
                fontSize = 10.sp,
                color = SecondaryText
            )
        }
    }
}

@Composable
private fun StateUpdateSection(
    currentState: String,
    newState: String,
    onStateChange: (String) -> Unit,
    onUpdateClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = CardBackground)
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "Actualizar Estado del Paciente",
                fontSize = 14.sp,
                color = PrimaryText,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Estado Actual: $currentState",
                fontSize = 12.sp,
                color = SecondaryText
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = newState,
                onValueChange = onStateChange,
                label = { Text("Nuevo estado") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onUpdateClick,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Actualizar Estado")
            }
        }
    }
}

