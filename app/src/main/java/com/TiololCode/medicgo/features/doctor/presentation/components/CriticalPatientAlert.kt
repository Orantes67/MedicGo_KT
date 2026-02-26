package com.TiololCode.medicgo.features.doctor.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient

private val CriticalBackground = Color(0xFFFFEBEE)
private val CriticalBorder = Color(0xFFEF5350)
private val CriticalText = Color(0xFFC62828)
private val SecondaryText = Color(0xFF888888)

@Composable
fun CriticalPatientAlert(patient: DoctorPatient) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = CriticalBackground,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Warning,
                contentDescription = "Critical Alert",
                tint = CriticalText,
                modifier = Modifier.width(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${patient.name} ${patient.lastName}",
                    fontSize = 14.sp,
                    color = CriticalText,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Actualizado: ${patient.lastUpdateTime}",
                    fontSize = 12.sp,
                    color = SecondaryText
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Área: ${patient.areaName}",
                    fontSize = 12.sp,
                    color = SecondaryText
                )
            }
        }
    }
}

