package com.TiololCode.medicgo.features.doctor.presentation.screens.patients

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient
import com.TiololCode.medicgo.features.doctor.domain.entities.PatientNote
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.PatientDetailUiState
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.PatientDetailViewModel

private val PrimaryText = Color(0xFF1A1A2E)
private val SubtitleText = Color(0xFF888888)
private val AccentBlue = Color(0xFF2979FF)
private val CardBackground = Color.White
private val ScreenBackground = Color(0xFFF5F7FA)
private val FieldBorder = Color(0xFFE0E0E0)
private val FieldBackground = Color(0xFFF9F9F9)

// ─── Sample data ─────────────────────────────────────────────────────────────

private val sampleNotes = listOf(
    PatientNote(
        id = 1L,
        patientId = 1L,
        doctorId = 1L,
        content = "Paciente presenta mejoría leve en la presión arterial tras medicación.",
        createdDate = "2024-11-16"
    ),
    PatientNote(
        id = 2L,
        patientId = 1L,
        doctorId = 1L,
        content = "Se realizó electrocardiograma. Resultados pendientes de revisión.",
        createdDate = "2024-11-17"
    )
)

private val samplePatientCritical = DoctorPatient(
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
)

private val samplePatientObservation = DoctorPatient(
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
)

private val samplePatientStable = DoctorPatient(
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

// ─── Public composable (uses ViewModel) ──────────────────────────────────────

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

// ─── Stateless composable (used by Previews too) ─────────────────────────────

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

// ─── Header ──────────────────────────────────────────────────────────────────

@Composable
private fun PatientDetailHeader(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardBackground)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color(0xFFF0F0F0))
                .clickable { onBackClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = PrimaryText,
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Detalles del Paciente",
            fontSize = 18.sp,
            color = PrimaryText,
            fontWeight = FontWeight.Bold
        )
    }
}

// ─── Basic info card ─────────────────────────────────────────────────────────

@Composable
private fun PatientBasicInfo(patient: DoctorPatient) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(CardBackground, RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF0F4FF)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = AccentBlue,
                    modifier = Modifier.size(26.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "${patient.name} ${patient.lastName}",
                    fontSize = 16.sp,
                    color = PrimaryText,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = patient.areaName,
                    fontSize = 13.sp,
                    color = SubtitleText
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(color = Color(0xFFF0F0F0))
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            InfoChip(label = "Edad", value = "${patient.age} años", modifier = Modifier.weight(1f))
            InfoChip(label = "Tipo de Sangre", value = patient.bloodType, modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(10.dp))
        InfoRow(label = "Estado Actual", value = patient.currentState, valueColor = stateColor(patient.currentState))
        InfoRow(label = "Síntomas", value = patient.symptoms)
        InfoRow(label = "Fecha de Registro", value = patient.registrationDate)
    }
}

@Composable
private fun InfoChip(label: String, value: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(ScreenBackground, RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        Column {
            Text(text = label, fontSize = 11.sp, color = SubtitleText)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = value, fontSize = 14.sp, color = PrimaryText, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String, valueColor: Color = PrimaryText) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(text = label, fontSize = 11.sp, color = SubtitleText, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = value, fontSize = 14.sp, color = valueColor)
    }
}

private fun stateColor(state: String): Color = when {
    state.contains("crít", ignoreCase = true) -> Color(0xFFE53935)
    state.contains("observ", ignoreCase = true) -> Color(0xFFFFA726)
    state.contains("estable", ignoreCase = true) -> Color(0xFF43A047)
    else -> PrimaryText
}

// ─── Nurse assignment ─────────────────────────────────────────────────────────

@Composable
private fun NurseAssignmentSection(patient: DoctorPatient) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(CardBackground, RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color(0xFFFCE4EC)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color(0xFFE91E8C),
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = "Enfermera Asignada", fontSize = 12.sp, color = SubtitleText)
            Text(
                text = patient.assignedNurseName,
                fontSize = 15.sp,
                color = PrimaryText,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

// ─── Notes ────────────────────────────────────────────────────────────────────

@Composable
private fun PatientNotesSection(
    notes: List<PatientNote>,
    newNoteContent: String,
    onNoteContentChange: (String) -> Unit,
    onAddNoteClick: () -> Unit
) {
    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = AccentBlue,
        unfocusedBorderColor = FieldBorder,
        focusedLabelColor = AccentBlue,
        unfocusedLabelColor = SubtitleText,
        unfocusedContainerColor = FieldBackground,
        focusedContainerColor = CardBackground
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(CardBackground, RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(text = "Notas del Paciente", fontSize = 15.sp, color = PrimaryText, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))

        if (notes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ScreenBackground, RoundedCornerShape(10.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Sin notas registradas", fontSize = 13.sp, color = SubtitleText)
            }
        } else {
            notes.forEach { note ->
                NoteItem(note = note)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = newNoteContent,
            onValueChange = onNoteContentChange,
            label = { Text("Agregar nueva nota") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = textFieldColors,
            maxLines = 3
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = onAddNoteClick,
            colors = ButtonDefaults.buttonColors(containerColor = AccentBlue),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Nota", fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun NoteItem(note: PatientNote) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(ScreenBackground, RoundedCornerShape(10.dp))
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Column {
            Text(text = note.content, fontSize = 13.sp, color = PrimaryText, lineHeight = 18.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = note.createdDate, fontSize = 11.sp, color = SubtitleText)
        }
    }
}

// ─── State update ─────────────────────────────────────────────────────────────

@Composable
private fun StateUpdateSection(
    currentState: String,
    newState: String,
    onStateChange: (String) -> Unit,
    onUpdateClick: () -> Unit
) {
    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = AccentBlue,
        unfocusedBorderColor = FieldBorder,
        focusedLabelColor = AccentBlue,
        unfocusedLabelColor = SubtitleText,
        unfocusedContainerColor = FieldBackground,
        focusedContainerColor = CardBackground
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(CardBackground, RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(text = "Actualizar Estado", fontSize = 15.sp, color = PrimaryText, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Estado actual:", fontSize = 12.sp, color = SubtitleText)
            Box(
                modifier = Modifier
                    .background(stateColor(currentState).copy(alpha = 0.12f), RoundedCornerShape(20.dp))
                    .padding(horizontal = 10.dp, vertical = 3.dp)
            ) {
                Text(
                    text = currentState,
                    fontSize = 12.sp,
                    color = stateColor(currentState),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = newState,
            onValueChange = onStateChange,
            label = { Text("Nuevo estado") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = textFieldColors,
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = onUpdateClick,
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryText),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar Estado", fontWeight = FontWeight.SemiBold)
        }
    }
}

// ─── Previews ────────────────────────────────────────────────────────────────

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
            notes = sampleNotes,
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

@Preview(showBackground = true, showSystemUi = true, name = "Paciente Observación - Sin notas")
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
            notes = sampleNotes,
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