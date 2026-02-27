package com.TiololCode.medicgo.features.doctor.presentation.screens.doctor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.TiololCode.medicgo.features.doctor.presentation.components.DoctorHeader
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.DoctorEvent
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.DoctorViewModel

private val ScreenBackground = Color(0xFFF5F7FA)

@Composable
fun DoctorScreen(
    viewModel: DoctorViewModel = hiltViewModel(),
    onPatientClick: (Long) -> Unit,
    onLogout: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is DoctorEvent.Error -> snackbarHostState.showSnackbar(event.message)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ScreenBackground)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            DoctorHeader(
                doctorName = uiState.doctorName,
                specialty = "Médico",
                onLogoutClick = onLogout
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                DoctorContent(
                    uiState = uiState,
                    onPatientClick = onPatientClick
                )
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

