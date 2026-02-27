package com.TiololCode.medicgo.features.doctor.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient

private val CardBackground = Color(0xFFFFFFFF)
private val PrimaryText = Color(0xFF1A1A2E)
private val SecondaryText = Color(0xFF888888)
private val DividerColor = Color(0xFFE0E0E0)

@Composable
fun PatientCard(
    patient: DoctorPatient,
    onClick: (DoctorPatient) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = CardBackground,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick(patient) }
            .padding(12.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = patient.fullName,
                        fontSize = 14.sp,
                        color = PrimaryText,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Edad: ${patient.age} años",
                        fontSize = 12.sp,
                        color = SecondaryText
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Box(
                    modifier = Modifier
                        .background(
                            color = when {
                                patient.currentState.contains("crít", ignoreCase = true) -> Color(0xFFFFEBEE)
                                patient.currentState.contains("observ", ignoreCase = true) -> Color(0xFFFFF3E0)
                                else -> Color(0xFFE8F5E9)
                            },
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = patient.currentState,
                        fontSize = 11.sp,
                        color = when {
                            patient.currentState.contains("crít", ignoreCase = true) -> Color(0xFFC62828)
                            patient.currentState.contains("observ", ignoreCase = true) -> Color(0xFFE65100)
                            else -> Color(0xFF2E7D32)
                        },
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = patient.area,
                fontSize = 12.sp,
                color = SecondaryText
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Nota: ${patient.conditionNote}",
                fontSize = 11.sp,
                color = SecondaryText,
                maxLines = 2
            )
        }
    }
}

