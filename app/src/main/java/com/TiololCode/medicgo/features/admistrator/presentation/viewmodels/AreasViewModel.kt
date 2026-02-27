package com.TiololCode.medicgo.features.admistrator.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.TiololCode.medicgo.features.admistrator.domain.entities.Area
import com.TiololCode.medicgo.features.admistrator.domain.entities.HealthProfessional
import com.TiololCode.medicgo.features.admistrator.domain.entities.Patient
import com.TiololCode.medicgo.features.admistrator.domain.usescases.AddPatientUseCase
import com.TiololCode.medicgo.features.admistrator.domain.usescases.GetAreasUseCase
import com.TiololCode.medicgo.features.admistrator.domain.usescases.GetHealthProfessionalsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AreasUiState(
    val areas: List<Area> = emptyList(),
    val doctors: List<HealthProfessional> = emptyList(),
    val nurses: List<HealthProfessional> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val addPatientSuccess: Boolean = false
)

@HiltViewModel
class AreasViewModel @Inject constructor(
    private val getAreasUseCase: GetAreasUseCase,
    private val addPatientUseCase: AddPatientUseCase,
    private val getHealthProfessionalsUseCase: GetHealthProfessionalsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AreasUiState())
    val uiState: StateFlow<AreasUiState> = _uiState.asStateFlow()

    init {
        loadAreas()
        loadProfessionals()
    }

    fun loadAreas() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = getAreasUseCase()
            result.fold(
                onSuccess = { areas ->
                    _uiState.update { it.copy(isLoading = false, areas = areas) }
                },
                onFailure = { error ->
                    _uiState.update { it.copy(isLoading = false, error = error.message ?: "Error desconocido") }
                }
            )
        }
    }

    fun loadProfessionals() {
        viewModelScope.launch {
            val result = getHealthProfessionalsUseCase()
            result.fold(
                onSuccess = { professionals ->
                    val doctors = professionals.filter { it.profession.contains("doctor", ignoreCase = true) }
                    val nurses = professionals.filter { it.profession.contains("enferm", ignoreCase = true) }
                    _uiState.update { it.copy(doctors = doctors, nurses = nurses) }
                },
                onFailure = { /* silent — areas still work without professionals */ }
            )
        }
    }

    fun addPatient(patient: Patient) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = addPatientUseCase(patient)
            result.fold(
                onSuccess = {
                    _uiState.update { it.copy(isLoading = false, addPatientSuccess = true) }
                },
                onFailure = { error ->
                    _uiState.update { it.copy(isLoading = false, error = error.message ?: "Error desconocido") }
                }
            )
        }
    }

    fun clearAddPatientSuccess() {
        _uiState.update { it.copy(addPatientSuccess = false) }
    }
}

