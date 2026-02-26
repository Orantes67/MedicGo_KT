package com.TiololCode.medicgo.features.doctor.presentation.screens.doctor.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.TiololCode.medicgo.features.doctor.presentation.components.CriticalPatientAlert

private val PrimaryText = Color(0xFF1A1A2E)

@Composable
fun CriticalAlertsSection(criticalPatients: List<DoctorPatient>) {
    if (criticalPatients.isNotEmpty()) {
        Column {
            CriticalAlertHeader()
            Spacer(modifier = Modifier.height(12.dp))

            criticalPatients.forEach { patient ->
                CriticalPatientAlert(patient = patient)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
internal fun CriticalAlertHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(Color(0xFFE53935))
        )
        Text(
            text = "Alertas — Pacientes Críticos",
            fontSize = 16.sp,
            color = PrimaryText,
            fontWeight = FontWeight.Bold
        )
    }
}

