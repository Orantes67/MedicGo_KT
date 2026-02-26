package com.TiololCode.medicgo.features.doctor.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val CardBackground = Color(0xFFFFFFFF)
private val CardBorder = Color(0xFFE0E0E0)
private val PrimaryText = Color(0xFF1A1A2E)
private val SecondaryText = Color(0xFF888888)

@Composable
fun MetricCard(
    title: String,
    value: Int,
    backgroundColor: Color = Color(0xFFE3F2FD)
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.45f)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = title,
                fontSize = 12.sp,
                color = SecondaryText,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value.toString(),
                fontSize = 32.sp,
                color = PrimaryText,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

