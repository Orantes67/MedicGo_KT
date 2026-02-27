package com.TiololCode.medicgo.features.admistrator.presentation.screens.areas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.TiololCode.medicgo.features.admistrator.domain.entities.Area

private val CardBackground = Color.White
private val PrimaryText = Color(0xFF1A1A2E)

@Suppress("unused")
@Composable
fun AreaList(
    areas: List<Area>,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            // header handled by parent if needed
        }

        items(areas) { area ->
            AreaCard(
                name = area.name,
                patientCount = area.patientCount,
                criticalCount = area.criticos
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                androidx.compose.material3.Button(
                    onClick = onAddClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    androidx.compose.material3.Text(
                        text = "Agregar Nuevo Paciente",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun AreaCard(
    name: String,
    patientCount: Int,
    criticalCount: Int = 0,
    maxCapacity: Int = 50
) {
    val fillFraction = (patientCount.toFloat() / maxCapacity.toFloat()).coerceIn(0f, 1f)
    val hasCritical = criticalCount > 0

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardBackground, RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.fillMaxWidth(0.75f)) {
                    androidx.compose.material3.Text(
                        text = name,
                        color = PrimaryText,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        androidx.compose.material3.Text(
                            text = "$patientCount pacientes",
                            color = Color(0xFF888888),
                            fontSize = 12.sp
                        )
                        if (hasCritical) {
                            androidx.compose.material3.Text(
                                text = " · ",
                                color = Color(0xFF888888),
                                fontSize = 12.sp
                            )
                            androidx.compose.material3.Text(
                                text = "+$criticalCount críticos",
                                color = Color(0xFFE53935),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (hasCritical) {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFE53935), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            androidx.compose.material3.Text(
                                text = "$criticalCount",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    androidx.compose.material3.Text(
                        text = "$patientCount",
                        color = PrimaryText,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(Color(0xFFEEEEEE), RoundedCornerShape(3.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fillFraction)
                        .height(6.dp)
                        .background(
                            if (hasCritical) Color(0xFFE53935)
                            else Color(0xFF2979FF), RoundedCornerShape(3.dp)
                        )
                )
            }
        }
    }
}
