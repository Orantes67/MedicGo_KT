package com.TiololCode.medicgo.features.doctor.presentation.screens.appointments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val ScreenBackground = Color(0xFFF5F7FA)
private val PrimaryText = Color(0xFF1A1A2E)

@Composable
fun AppointmentsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ScreenBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Pantalla de Citas",
                fontSize = 20.sp,
                color = PrimaryText,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Este módulo estará disponible próximamente",
                fontSize = 14.sp,
                color = Color(0xFF888888)
            )
        }
    }
}

