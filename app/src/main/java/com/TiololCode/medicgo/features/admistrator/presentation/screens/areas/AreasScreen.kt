package com.TiololCode.medicgo.features.admistrator.presentation.screens.areas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.TiololCode.medicgo.features.admistrator.presentation.viewmodels.AreasViewModel

private val ScreenBackground = Color(0xFFF5F7FA)

@Composable
fun AreasScreen(viewModel: AreasViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var showDialog by rememberSaveable { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().background(ScreenBackground)) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = Color(0xFF2979FF))
            }

            uiState.error != null -> {
                Text(text = "Error: ${uiState.error}", color = Color(0xFFE53935), modifier = Modifier.align(Alignment.Center))
            }

            else -> {
                AreaList(areas = uiState.areas, onAddClick = { showDialog = true }, modifier = Modifier.padding(0.dp))
            }
        }

        if (showDialog) {
            AddPatientDialog(
                areas = uiState.areas,
                initialAreaIndex = 0,
                onDismiss = { showDialog = false },
                onSave = { patient ->
                    viewModel.addPatient(patient)
                }
            )
        }
    }
}

