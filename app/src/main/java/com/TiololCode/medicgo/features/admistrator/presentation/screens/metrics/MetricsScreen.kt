package com.TiololCode.medicgo.features.admistrator.presentation.screens.metrics

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.TiololCode.medicgo.features.admistrator.presentation.viewmodels.MetricsViewModel

private val ScreenBackground = Color(0xFFF5F7FA)
private val PrimaryText = Color(0xFF1A1A2E)
private val CardBackground = Color.White

@Composable
fun MetricsScreen(viewModel: MetricsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackground)
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color(0xFF2979FF)
                )
            }

            uiState.error != null -> {
                Text(
                    text = "Error: ${uiState.error}",
                    color = Color(0xFFE53935),
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            uiState.metric != null -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        MetricCardLarge(
                            title = "Total Pacientes",
                            value = uiState.metric!!.totalPatients.toString(),
                            subtitle = "↗ Activos",
                            accentColor = Color(0xFF2979FF)
                        )
                    }
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                MetricCardSmall(
                                    title = "Críticos",
                                    value = uiState.metric!!.criticalPatients.toString(),
                                    subtitle = "${uiState.metric!!.criticalPatients} emergencias",
                                    accentColor = Color(0xFFE53935),
                                    dotColor = Color(0xFFE53935)
                                )
                            }
                            Box(modifier = Modifier.weight(1f)) {
                                MetricCardSmall(
                                    title = "Observación",
                                    value = uiState.metric!!.observationPatients.toString(),
                                    subtitle = "En monitoreo",
                                    accentColor = Color(0xFFFFA726),
                                    dotColor = Color(0xFFFFA726)
                                )
                            }
                        }
                    }
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                MetricCardSmall(
                                    title = "Estables",
                                    value = "2",
                                    subtitle = "Sin novedades",
                                    accentColor = Color(0xFF43A047),
                                    dotColor = Color(0xFF43A047)
                                )
                            }
                            Box(modifier = Modifier.weight(1f)) {
                                MetricCardSmall(
                                    title = "Personal Activo",
                                    value = "4",
                                    subtitle = "2 enfermeras · 2 doctores",
                                    accentColor = Color(0xFF8E24AA),
                                    dotColor = Color(0xFF8E24AA)
                                )
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Actividad Reciente",
                            color = PrimaryText,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun MetricCardLarge(
    title: String,
    value: String,
    subtitle: String = "",
    accentColor: Color = Color(0xFF2979FF)
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardBackground, RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(16.dp))
            .padding(20.dp)
    ) {
        Column {
            Text(
                text = title,
                color = Color(0xFF888888),
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = value,
                color = PrimaryText,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
            if (subtitle.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    color = accentColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun MetricCardSmall(
    title: String,
    value: String,
    subtitle: String = "",
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
                    color = accentColor,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                color = PrimaryText,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            if (subtitle.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    color = Color(0xFF888888),
                    fontSize = 11.sp
                )
            }
        }
    }
}

