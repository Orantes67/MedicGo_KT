package com.TiololCode.medicgo.features.doctor.presentation.screens.doctor

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.DoctorViewModel
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.DoctorUiState
import com.TiololCode.medicgo.features.doctor.presentation.components.CriticalPatientAlert

private val PrimaryText = Color(0xFF1A1A2E)
private val CardBackground = Color.White

@Composable
fun DoctorContent(
    uiState: DoctorUiState,
    viewModel: DoctorViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            MetricsSection(metrics = uiState.metrics)
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
            CriticalAlertsSection(criticalPatients = uiState.criticalPatients)
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Lista de Pacientes",
                    fontSize = 16.sp,
                    color = PrimaryText,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier
                        .background(Color(0xFFF0F4FF), RoundedCornerShape(20.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "${uiState.patients.size} pacientes",
                        fontSize = 12.sp,
                        color = Color(0xFF2979FF),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        items(uiState.patients.size) { index ->
            PatientListItem(
                patient = uiState.patients[index],
                onClick = { viewModel.selectPatient(it) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

@Composable
private fun MetricsSection(metrics: com.TiololCode.medicgo.features.doctor.domain.entities.DoctorMetric) {
    Column {
        Text(
            text = "Métricas",
            fontSize = 16.sp,
            color = PrimaryText,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                MetricCardStyled(
                    title = "Total Pacientes",
                    value = metrics.totalPatients.toString(),
                    accentColor = Color(0xFF2979FF),
                    dotColor = Color(0xFF2979FF)
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                MetricCardStyled(
                    title = "Observación",
                    value = metrics.patientsUnderObservation.toString(),
                    accentColor = Color(0xFFFFA726),
                    dotColor = Color(0xFFFFA726)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                MetricCardStyled(
                    title = "Críticos",
                    value = metrics.criticalPatients.toString(),
                    accentColor = Color(0xFFE53935),
                    dotColor = Color(0xFFE53935)
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                MetricCardStyled(
                    title = "Estables",
                    value = metrics.stablePatients.toString(),
                    accentColor = Color(0xFF43A047),
                    dotColor = Color(0xFF43A047)
                )
            }
        }
    }
}

@Composable
private fun MetricCardStyled(
    title: String,
    value: String,
    accentColor: Color,
    dotColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardBackground, RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(dotColor)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = title,
                    fontSize = 12.sp,
                    color = accentColor,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                fontSize = 28.sp,
                color = PrimaryText,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun CriticalAlertsSection(criticalPatients: List<com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient>) {
    if (criticalPatients.isNotEmpty()) {
        Column {
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
            Spacer(modifier = Modifier.height(12.dp))

            criticalPatients.forEach { patient ->
                CriticalPatientAlert(patient = patient)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun PatientListItem(
    patient: com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient,
    onClick: (com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient) -> Unit
) {
    com.TiololCode.medicgo.features.doctor.presentation.components.PatientCard(
        patient = patient,
        onClick = onClick
    )
}