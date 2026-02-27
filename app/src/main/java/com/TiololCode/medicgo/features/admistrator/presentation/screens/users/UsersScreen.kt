@file:Suppress("DEPRECATION")

package com.TiololCode.medicgo.features.admistrator.presentation.screens.users

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.TiololCode.medicgo.features.admistrator.presentation.viewmodels.UsersViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon

private val ScreenBackground = Color(0xFFF5F7FA)
private val PrimaryText = Color(0xFF1A1A2E)
private val CardBackground = Color.White

@Composable
fun UsersScreen(viewModel: UsersViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackground)
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color(0xFF2979FF)
                )
            }

            uiState.error != null -> {
                Text(
                    text = "Error: ${uiState.error}",
                    color = Color(0xFFE53935),
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "Gestión de Usuarios",
                            color = PrimaryText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        item {
                            SectionHeader(
                                title = "Enfermeras (${uiState.professionals.count { it.profession.contains("enferm", ignoreCase = true) }})",
                                accentColor = Color(0xFFE91E8C)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        items(uiState.professionals.filter { it.profession.contains("enferm", ignoreCase = true) }) { professional ->
                            ProfessionalCard(
                                name = professional.name,
                                profession = professional.profession,
                                licenseNumber = professional.licenseNumber,
                                email = professional.email,
                                specialty = professional.specialty
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                            SectionHeader(
                                title = "Doctores (${uiState.professionals.count { it.profession.contains("doctor", ignoreCase = true) || it.profession.contains("Dr.", ignoreCase = true) }})",
                                accentColor = Color(0xFF9C27B0)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        items(uiState.professionals.filter { !it.profession.contains("enferm", ignoreCase = true) }) { professional ->
                            ProfessionalCard(
                                name = professional.name,
                                profession = professional.profession,
                                licenseNumber = professional.licenseNumber,
                                email = professional.email,
                                specialty = professional.specialty
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, accentColor: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(accentColor.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.foundation.layout.Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(accentColor)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            color = PrimaryText,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun ProfessionalCard(
    name: String,
    profession: String,
    licenseNumber: String,
    email: String = "",
    specialty: String = ""
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardBackground, RoundedCornerShape(14.dp))
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(14.dp))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFF0F4FF)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color(0xFF2979FF),
                modifier = Modifier.size(22.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name.ifEmpty { "Sin nombre" },
                color = PrimaryText,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
            if (specialty.isNotEmpty()) {
                Text(
                    text = specialty,
                    color = Color(0xFF2979FF),
                    fontSize = 12.sp
                )
            } else {
                Text(
                    text = profession,
                    color = Color(0xFF888888),
                    fontSize = 13.sp
                )
            }
            if (email.isNotEmpty()) {
                Text(
                    text = email,
                    color = Color(0xFF888888),
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Lic: $licenseNumber",
                color = Color(0xFFBBBBBB),
                fontSize = 11.sp
            )
        }
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Ver perfil",
                tint = Color(0xFF888888),
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
