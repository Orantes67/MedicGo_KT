package com.TiololCode.medicgo.features.doctor.presentation.screens.doctor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.DoctorViewModel
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.DoctorUiState
import com.TiololCode.medicgo.features.doctor.presentation.components.MetricCard
import com.TiololCode.medicgo.features.doctor.presentation.components.CriticalPatientAlert

private val PrimaryText = Color(0xFF1A1A2E)
private val SecondaryText = Color(0xFF888888)

@Composable
fun DoctorContent(
    uiState: DoctorUiState,
    viewModel: DoctorViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        item {
            MetricsSection(metrics = uiState.metrics)
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            CriticalAlertsSection(criticalPatients = uiState.criticalPatients)
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            Text(
                text = "Lista de Pacientes",
                fontSize = 16.sp,
                color = PrimaryText,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        items(uiState.patients.size) { index ->
            PatientListItem(
                patient = uiState.patients[index],
                onClick = { viewModel.selectPatient(it) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
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

        Row(modifier = Modifier.fillMaxWidth()) {
            MetricCard(
                title = "Total de Pacientes",
                value = metrics.totalPatients,
                backgroundColor = Color(0xFFE3F2FD)
            )
            Spacer(modifier = Modifier.weight(1f))
            MetricCard(
                title = "En Observación",
                value = metrics.patientsUnderObservation,
                backgroundColor = Color(0xFFFFF3E0)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            MetricCard(
                title = "Críticos",
                value = metrics.criticalPatients,
                backgroundColor = Color(0xFFFFEBEE)
            )
            Spacer(modifier = Modifier.weight(1f))
            MetricCard(
                title = "Estables",
                value = metrics.stablePatients,
                backgroundColor = Color(0xFFE8F5E9)
            )
        }
    }
}

@Composable
private fun CriticalAlertsSection(criticalPatients: List<com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient>) {
    if (criticalPatients.isNotEmpty()) {
        Column {
            Text(
                text = "Alertas - Pacientes Críticos",
                fontSize = 16.sp,
                color = PrimaryText,
                fontWeight = FontWeight.Bold
            )
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

