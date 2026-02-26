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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
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
private val DividerColor = Color(0xFFF0F0F0)

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
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(20.dp)
                .fillMaxWidth(0.9f)
        ) {
            LazyColumn {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${patient.name} ${patient.lastName}",
                                fontSize = 20.sp,
                                color = PrimaryText,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "ID: ${patient.id}",
                                fontSize = 11.sp,
                                color = SecondaryText,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Cerrar",
                            modifier = Modifier
                                .clickable { onDismiss() }
                                .width(24.dp)
                                .height(24.dp),
                            tint = SecondaryText
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    // Información básica
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
                    ) {
                        InfoCard("Edad", "${patient.age} años", Modifier.weight(1f))
                        InfoCard("Tipo de Sangre", patient.bloodType, Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    // Área y Estado
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
                    ) {
                        InfoCard("Área", patient.areaName, Modifier.weight(1f))
                        InfoCard("Estado", patient.currentState, Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    // Divider
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(DividerColor)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    Text(
                        text = "Síntomas",
                        fontSize = 13.sp,
                        color = PrimaryText,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = patient.symptoms,
                        fontSize = 12.sp,
                        color = SecondaryText,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    Text(
                        text = "Nota de Condición",
                        fontSize = 13.sp,
                        color = PrimaryText,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = patient.conditionNote,
                        fontSize = 12.sp,
                        color = SecondaryText,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    // Divider
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(DividerColor)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    // Personal asignado
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
                    ) {
                        InfoCard("Enfermera", patient.assignedNurseName, Modifier.weight(1f))
                        InfoCard("Fecha Registro", patient.registrationDate, Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    Text(
                        text = "Última Actualización",
                        fontSize = 13.sp,
                        color = PrimaryText,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = patient.lastUpdateTime,
                        fontSize = 12.sp,
                        color = SecondaryText,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun InfoCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = Color(0xFFF5F7FA),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = label,
            fontSize = 10.sp,
            color = SecondaryText,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = value,
            fontSize = 12.sp,
            color = PrimaryText,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

