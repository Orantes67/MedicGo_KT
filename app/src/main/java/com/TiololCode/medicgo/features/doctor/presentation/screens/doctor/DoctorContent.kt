package com.TiololCode.medicgo.features.doctor.presentation.screens.doctor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.DoctorViewModel
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.DoctorUiState
import com.TiololCode.medicgo.features.doctor.presentation.screens.doctor.sections.MetricsSection
import com.TiololCode.medicgo.features.doctor.presentation.screens.doctor.sections.CriticalAlertsSection
import com.TiololCode.medicgo.features.doctor.presentation.components.PatientCard

private val PrimaryText = Color(0xFF1A1A2E)

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
        // Métricas
        item {
            Spacer(modifier = Modifier.height(16.dp))
            MetricsSection(metrics = uiState.metrics)
        }

        // Alertas críticas
        item {
            Spacer(modifier = Modifier.height(20.dp))
            CriticalAlertsSection(criticalPatients = uiState.criticalPatients)
        }

        // Header de lista de pacientes
        item {
            Spacer(modifier = Modifier.height(20.dp))
            PatientListHeader(patientCount = uiState.patients.size)
            Spacer(modifier = Modifier.height(12.dp))
        }

        // Lista de pacientes
        items(uiState.patients.size) { index ->
            PatientCard(
                patient = uiState.patients[index],
                onClick = { viewModel.selectPatient(it) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Espacio final
        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

@Composable
private fun PatientListHeader(patientCount: Int) {
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
                text = "$patientCount pacientes",
                fontSize = 12.sp,
                color = Color(0xFF2979FF),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
