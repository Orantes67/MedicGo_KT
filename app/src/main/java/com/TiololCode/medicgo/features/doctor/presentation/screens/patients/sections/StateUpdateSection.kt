package com.TiololCode.medicgo.features.doctor.presentation.screens.patients.sections

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val PrimaryText = Color(0xFF1A1A2E)
private val SubtitleText = Color(0xFF888888)
private val CardBackground = Color.White

private val stateOptions = listOf("estable", "observacion", "critico")
private val stateLabels = mapOf(
    "estable"     to "Estable",
    "observacion" to "Observaci\u00f3n",
    "critico"     to "Cr\u00edtico"
)

@Composable
fun StateUpdateSection(
    currentState: String,
    selectedState: String,
    onStateSelect: (String) -> Unit,
    onConfirmClick: () -> Unit,
    isUpdatingState: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(CardBackground, RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "Actualizar Estado",
            fontSize = 15.sp,
            color = PrimaryText,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        CurrentStateDisplay(currentState = currentState)

        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            stateOptions.forEach { state ->
                val isSelected = selectedState.lowercase() == state
                val color = stateColor(state)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = if (isSelected) color.copy(alpha = 0.15f) else Color(0xFFF5F7FA),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .border(
                            width = if (isSelected) 2.dp else 1.dp,
                            color = if (isSelected) color else Color(0xFFE0E0E0),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable { onStateSelect(state) }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stateLabels[state] ?: state,
                        fontSize = 12.sp,
                        color = if (isSelected) color else SubtitleText,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = onConfirmClick,
            enabled = !isUpdatingState,
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryText),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if (isUpdatingState) "Actualizando..." else "Confirmar Estado",
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun CurrentStateDisplay(currentState: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Estado actual:", fontSize = 12.sp, color = SubtitleText)
        Box(
            modifier = Modifier
                .background(stateColor(currentState).copy(alpha = 0.12f), RoundedCornerShape(20.dp))
                .padding(horizontal = 10.dp, vertical = 3.dp)
        ) {
            Text(
                text = currentState,
                fontSize = 12.sp,
                color = stateColor(currentState),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

