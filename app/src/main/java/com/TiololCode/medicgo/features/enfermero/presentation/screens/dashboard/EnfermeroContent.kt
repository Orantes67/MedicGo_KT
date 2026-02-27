package com.TiololCode.medicgo.features.enfermero.presentation.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import com.TiololCode.medicgo.features.enfermero.domain.entities.EnfermeroPatient
import com.TiololCode.medicgo.features.enfermero.presentation.components.EnfermeroPatientCard
import com.TiololCode.medicgo.features.enfermero.presentation.screens.dashboard.sections.EnfermeroStatsSection
import com.TiololCode.medicgo.features.enfermero.presentation.viewmodels.EnfermeroUiState

private val PrimaryText = Color(0xFF1A1A2E)

@Composable
fun EnfermeroContent(
    uiState: EnfermeroUiState,
    onPatientClick: (EnfermeroPatient) -> Unit,
    onStateSelect: (String) -> Unit,
    onNotaChange: (String) -> Unit,
    onCancelar: () -> Unit,
    onActualizar: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Stats cards
        item {
            Spacer(modifier = Modifier.height(16.dp))
            EnfermeroStatsSection(metrics = uiState.metrics)
            Spacer(modifier = Modifier.height(20.dp))
        }

        // Patients header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Mis pacientes asignados",
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

        // Loading
        if (uiState.isLoading) {
            item {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }

        // Patient cards
        items(uiState.patients.size) { index ->
            val patient = uiState.patients[index]
            EnfermeroPatientCard(
                patient = patient,
                isExpanded = uiState.selectedPatientId == patient.id,
                selectedState = if (uiState.selectedPatientId == patient.id) uiState.selectedState else "",
                notaRapida = if (uiState.selectedPatientId == patient.id) uiState.notaRapida else "",
                isUpdating = uiState.isUpdating,
                onCardClick = { onPatientClick(patient) },
                onStateSelect = onStateSelect,
                onNotaChange = onNotaChange,
                onCancelar = onCancelar,
                onActualizar = onActualizar
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}
