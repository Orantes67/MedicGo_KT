package com.TiololCode.medicgo.features.doctor.presentation.screens.patients.sections

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
private val AccentBlue = Color(0xFF2979FF)
private val CardBackground = Color.White
private val FieldBorder = Color(0xFFE0E0E0)
private val FieldBackground = Color(0xFFF9F9F9)

@Composable
fun StateUpdateSection(
    currentState: String,
    newState: String,
    onStateChange: (String) -> Unit,
    onUpdateClick: () -> Unit
) {
    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = AccentBlue,
        unfocusedBorderColor = FieldBorder,
        focusedLabelColor = AccentBlue,
        unfocusedLabelColor = SubtitleText,
        unfocusedContainerColor = FieldBackground,
        focusedContainerColor = CardBackground
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(CardBackground, RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(text = "Actualizar Estado", fontSize = 15.sp, color = PrimaryText, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        CurrentStateDisplay(currentState = currentState)

        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = newState,
            onValueChange = onStateChange,
            label = { Text("Nuevo estado") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = textFieldColors,
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = onUpdateClick,
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryText),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar Estado", fontWeight = FontWeight.SemiBold)
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

