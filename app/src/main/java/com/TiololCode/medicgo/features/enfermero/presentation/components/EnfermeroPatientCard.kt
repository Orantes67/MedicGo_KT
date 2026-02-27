package com.TiololCode.medicgo.features.enfermero.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.TiololCode.medicgo.features.enfermero.domain.entities.EnfermeroPatient

private val CardBackground = Color(0xFFFFFFFF)
private val PrimaryText = Color(0xFF1A1A2E)
private val SecondaryText = Color(0xFF888888)
private val AccentBlue = Color(0xFF2979FF)
private val AccentGreen = Color(0xFF2E7D32)
private val AccentOrange = Color(0xFFE65100)
private val AccentRed = Color(0xFFC62828)

private fun stateColor(state: String): Pair<Color, Color> = when {
    state.contains("crít", ignoreCase = true)  -> Color(0xFFFFEBEE) to AccentRed
    state.contains("observ", ignoreCase = true) -> Color(0xFFFFF3E0) to AccentOrange
    else                                         -> Color(0xFFE8F5E9) to AccentGreen
}

@Composable
fun EnfermeroPatientCard(
    patient: EnfermeroPatient,
    isExpanded: Boolean,
    selectedState: String,
    notaRapida: String,
    isUpdating: Boolean,
    onCardClick: () -> Unit,
    onStateSelect: (String) -> Unit,
    onNotaChange: (String) -> Unit,
    onCancelar: () -> Unit,
    onActualizar: () -> Unit
) {
    val (bgColor, textColor) = stateColor(patient.currentState)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = CardBackground, shape = RoundedCornerShape(12.dp))
            .border(width = 1.dp, color = Color(0xFFE0E0E0), shape = RoundedCornerShape(12.dp))
            .clickable { onCardClick() }
            .padding(12.dp)
    ) {
        // ── Collapsed section (always visible) ──────────────────────────
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(patient.fullName, fontSize = 14.sp, color = PrimaryText, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(2.dp))
                Text("${patient.age} años · ${patient.code}", fontSize = 12.sp, color = SecondaryText)
                Spacer(modifier = Modifier.height(2.dp))
                Text(patient.area, fontSize = 12.sp, color = SecondaryText)
            }
            Box(
                modifier = Modifier
                    .background(color = bgColor, shape = RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(text = patient.currentState, fontSize = 11.sp, color = textColor, fontWeight = FontWeight.SemiBold)
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text("Próxima cita: ${patient.nextAppointment}", fontSize = 11.sp, color = SecondaryText)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Nota: ${patient.conditionNote}", fontSize = 11.sp, color = SecondaryText, maxLines = 2)

        // ── Expanded section ─────────────────────────────────────────────
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Column {
                Spacer(modifier = Modifier.height(12.dp))

                // Estado actual banner
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = bgColor, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Estado Actual:", fontSize = 12.sp, color = SecondaryText, modifier = Modifier.weight(1f))
                    Text(patient.currentState, fontSize = 13.sp, color = textColor, fontWeight = FontWeight.SemiBold)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Nuevo estado chips
                Text("Nuevo Estado", fontSize = 12.sp, color = SecondaryText, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("Estable", "Observación", "Crítico").forEach { estado ->
                        val isSelected = selectedState.equals(estado, ignoreCase = true)
                        val chipBg = if (isSelected) when (estado) {
                            "Crítico"     -> Color(0xFFC62828)
                            "Observación" -> Color(0xFFE65100)
                            else          -> Color(0xFF2E7D32)
                        } else Color(0xFFF0F0F0)
                        val chipText = if (isSelected) Color.White else Color(0xFF555555)

                        Box(
                            modifier = Modifier
                                .background(color = chipBg, shape = RoundedCornerShape(20.dp))
                                .clickable { onStateSelect(estado) }
                                .padding(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Text(estado, fontSize = 12.sp, color = chipText, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Nota rápida
                Text("Nota Rápida (opcional)", fontSize = 12.sp, color = SecondaryText, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(8.dp))
                        .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 10.dp)
                ) {
                    if (notaRapida.isEmpty()) {
                        Text("Agregar una nota rápida...", fontSize = 12.sp, color = Color(0xFFAAAAAA))
                    }
                    BasicTextField(
                        value = notaRapida,
                        onValueChange = onNotaChange,
                        textStyle = TextStyle(fontSize = 13.sp, color = PrimaryText),
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2,
                        maxLines = 4
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Action buttons
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(
                        onClick = onCancelar,
                        modifier = Modifier.weight(1f),
                        enabled = !isUpdating
                    ) { Text("Cancelar", fontSize = 13.sp) }

                    Button(
                        onClick = onActualizar,
                        modifier = Modifier.weight(1f),
                        enabled = !isUpdating && selectedState.isNotBlank(),
                        colors = ButtonDefaults.buttonColors(containerColor = AccentBlue)
                    ) { Text("Actualizar Estado", fontSize = 13.sp) }
                }
            }
        }
    }
}
