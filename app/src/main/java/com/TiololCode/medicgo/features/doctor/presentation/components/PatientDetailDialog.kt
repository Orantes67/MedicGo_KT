package com.TiololCode.medicgo.features.doctor.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient

private val DialogBackground = Color(0xFFFFFFFF)
private val PrimaryText = Color(0xFF1A1A2E)
private val SecondaryText = Color(0xFF888888)

@Composable
fun PatientDetailDialog(
    patient: DoctorPatient,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = DialogBackground,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "${patient.name} ${patient.lastName}",
                    fontSize = 18.sp,
                    color = PrimaryText,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Edad: ${patient.age} años | Área: ${patient.areaName}",
                    fontSize = 12.sp,
                    color = SecondaryText,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Text(
                    text = "Estado: ${patient.currentState}",
                    fontSize = 12.sp,
                    color = SecondaryText,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Text(
                    text = "Enfermera: ${patient.assignedNurseName}",
                    fontSize = 12.sp,
                    color = SecondaryText,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

