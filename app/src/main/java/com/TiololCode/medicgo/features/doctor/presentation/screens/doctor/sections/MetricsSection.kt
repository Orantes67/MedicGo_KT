package com.TiololCode.medicgo.features.doctor.presentation.screens.doctor.sections

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorMetric

private val PrimaryText = Color(0xFF1A1A2E)
private val CardBackground = Color.White

@Composable
fun MetricsSection(metrics: DoctorMetric) {
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
                MetricCard(
                    title = "Total Pacientes",
                    value = metrics.total.toString(),
                    accentColor = Color(0xFF2979FF),
                    dotColor = Color(0xFF2979FF)
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                MetricCard(
                    title = "Observación",
                    value = metrics.observation.toString(),
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
                MetricCard(
                    title = "Críticos",
                    value = metrics.critical.toString(),
                    accentColor = Color(0xFFE53935),
                    dotColor = Color(0xFFE53935)
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                MetricCard(
                    title = "Estables",
                    value = metrics.stable.toString(),
                    accentColor = Color(0xFF43A047),
                    dotColor = Color(0xFF43A047)
                )
            }
        }
    }
}

@Composable
internal fun MetricCard(
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

