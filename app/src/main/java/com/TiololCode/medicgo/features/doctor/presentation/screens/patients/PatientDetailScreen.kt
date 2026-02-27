package com.TiololCode.medicgo.features.doctor.presentation.screens.patients

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.PatientDetailEvent
import com.TiololCode.medicgo.features.doctor.presentation.viewmodels.PatientDetailViewModel

private val ScreenBackground = Color(0xFFF5F7FA)

@Composable
fun PatientDetailScreen(
    patientId: Long,
    viewModel: PatientDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(patientId) {
        viewModel.loadDetail(patientId)
    }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is PatientDetailEvent.Error -> snackbarHostState.showSnackbar(event.message)
                PatientDetailEvent.StateUpdated -> snackbarHostState.showSnackbar("Estado actualizado")
                PatientDetailEvent.NoteAdded -> snackbarHostState.showSnackbar("Nota agregada")
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ScreenBackground)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            PatientDetailContent(
                uiState = uiState,
                viewModel = viewModel,
                onBackClick = onBackClick
            )
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
