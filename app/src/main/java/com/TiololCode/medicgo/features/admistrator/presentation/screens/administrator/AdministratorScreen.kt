// Archivo de pantalla principal del administrador
package com.TiololCode.medicgo.features.admistrator.presentation.screens.administrator

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
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
import com.TiololCode.medicgo.features.admistrator.presentation.viewmodels.AdministratorViewModel
import com.TiololCode.medicgo.features.admistrator.presentation.viewmodels.AdminTab
import com.TiololCode.medicgo.features.admistrator.presentation.screens.metrics.MetricsScreen
import com.TiololCode.medicgo.features.admistrator.presentation.screens.users.UsersScreen
import com.TiololCode.medicgo.features.admistrator.presentation.screens.areas.AreasScreen
import androidx.compose.foundation.clickable

private val ScreenBackground = Color(0xFFF5F7FA)
private val PrimaryText = Color(0xFF1A1A2E)
private val SubtitleText = Color(0xFF888888)
private val TabActiveBackground = Color(0xFF1A1A2E)
private val TabInactiveBackground = Color.Transparent

@Composable
fun AdministratorScreen(viewModel: AdministratorViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackground)
    ) {
        AdministratorHeader()
        AdministratorTabBar(
            selectedTab = uiState.selectedTab,
            onTabSelected = { viewModel.onTabSelected(it) }
        )
        Box(modifier = Modifier.fillMaxSize()) {
            when (uiState.selectedTab) {
                AdminTab.METRICS -> MetricsScreen()
                AdminTab.USERS -> UsersScreen()
                AdminTab.AREAS -> AreasScreen()
            }
        }
    }
}

@Composable
fun AdministratorHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color(0xFFF0F0F0))
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = PrimaryText,
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Panel Administrativo",
                color = PrimaryText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Doctor",
                color = SubtitleText,
                fontSize = 13.sp
            )
        }
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color(0xFFF0F0F0))
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = PrimaryText,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun AdministratorTabBar(
    selectedTab: AdminTab,
    onTabSelected: (AdminTab) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(12.dp))
            .background(Color(0xFFF5F7FA), RoundedCornerShape(12.dp))
            .padding(4.dp)
    ) {
        AdminTab.entries.forEach { tab ->
            val isSelected = selectedTab == tab
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(36.dp)
                    .clip(RoundedCornerShape(9.dp))
                    .background(if (isSelected) TabActiveBackground else TabInactiveBackground)
                    .clickable { onTabSelected(tab) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when (tab) {
                        AdminTab.METRICS -> "Métricas"
                        AdminTab.USERS -> "Usuarios"
                        AdminTab.AREAS -> "Áreas"
                    },
                    color = if (isSelected) Color.White else Color(0xFF888888),
                    fontSize = 13.sp,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                )
            }
        }
    }
}
