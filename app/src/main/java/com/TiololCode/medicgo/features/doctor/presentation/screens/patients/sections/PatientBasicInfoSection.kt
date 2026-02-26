package com.TiololCode.medicgo.features.doctor.presentation.screens.patients.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient

private val PrimaryText = Color(0xFF1A1A2E)
private val SubtitleText = Color(0xFF888888)
private val AccentBlue = Color(0xFF2979FF)
private val CardBackground = Color.White
private val ScreenBackground = Color(0xFFF5F7FA)

@Composable
fun PatientBasicInfo(patient: DoctorPatient) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(CardBackground, RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        PatientNameRow(patient = patient)
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(color = Color(0xFFF0F0F0))
        Spacer(modifier = Modifier.height(12.dp))
        PatientInfoChips(patient = patient)
    }
}

@Composable
private fun PatientNameRow(patient: DoctorPatient) {
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
}

@Composable
private fun PatientInfoChips(patient: DoctorPatient) {
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

@Composable
internal fun InfoChip(label: String, value: String, modifier: Modifier = Modifier) {
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
internal fun InfoRow(label: String, value: String, valueColor: Color = PrimaryText) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(text = label, fontSize = 11.sp, color = SubtitleText, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = value, fontSize = 14.sp, color = valueColor)
    }
}

internal fun stateColor(state: String): Color = when {
    state.contains("crít", ignoreCase = true) -> Color(0xFFE53935)
    state.contains("observ", ignoreCase = true) -> Color(0xFFFFA726)
    state.contains("estable", ignoreCase = true) -> Color(0xFF43A047)
    else -> PrimaryText
}

