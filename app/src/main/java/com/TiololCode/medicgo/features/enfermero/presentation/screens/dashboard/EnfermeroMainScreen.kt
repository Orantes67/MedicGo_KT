package com.TiololCode.medicgo.features.enfermero.presentation.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.TiololCode.medicgo.features.enfermero.presentation.components.EnfermeroHeader
import com.TiololCode.medicgo.features.enfermero.presentation.viewmodels.EnfermeroEvent
import com.TiololCode.medicgo.features.enfermero.presentation.viewmodels.EnfermeroViewModel

private val ScreenBackground = Color(0xFFF5F7FA)

@Composable
fun EnfermeroMainScreen(
    viewModel: EnfermeroViewModel = hiltViewModel(),
    onLogout: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is EnfermeroEvent.EstadoActualizado ->
                    snackbarHostState.showSnackbar("Estado actualizado correctamente")
                is EnfermeroEvent.NotaAgregada ->
                    snackbarHostState.showSnackbar("Nota agregada correctamente")
                is EnfermeroEvent.Error ->
                    snackbarHostState.showSnackbar(event.message)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = ScreenBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            EnfermeroHeader(
                nurseName = uiState.nurseName,
                onLogoutClick = onLogout
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                EnfermeroContent(
                    uiState = uiState,
                    onPatientClick = { patient ->
                        if (uiState.selectedPatientId == patient.id) {
                            viewModel.selectPatient(null)     // collapse
                        } else {
                            viewModel.selectPatient(patient)  // expand
                        }
                    },
                    onStateSelect = viewModel::onStateSelect,
                    onNotaChange = viewModel::onNotaChange,
                    onCancelar = { viewModel.selectPatient(null) },
                    onActualizar = viewModel::confirmUpdate
                )
            }
        }
    }
}
