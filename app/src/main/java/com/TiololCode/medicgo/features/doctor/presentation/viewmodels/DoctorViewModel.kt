package com.TiololCode.medicgo.features.doctor.presentation.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorMetric
import javax.inject.Inject

data class DoctorUiState(
    val doctorName: String = "",
    val doctorSpecialty: String = "",
    val metrics: DoctorMetric = DoctorMetric(0, 0, 0, 0),
    val patients: List<DoctorPatient> = emptyList(),
    val criticalPatients: List<DoctorPatient> = emptyList(),
    val selectedPatient: DoctorPatient? = null,
    val isLoadingPatients: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class DoctorViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(DoctorUiState())
    val uiState: StateFlow<DoctorUiState> = _uiState.asStateFlow()

    fun updateDoctorInfo(name: String, specialty: String) {
        _uiState.update {
            it.copy(
                doctorName = name,
                doctorSpecialty = specialty
            )
        }
    }

    fun updateMetrics(metrics: DoctorMetric) {
        _uiState.update { it.copy(metrics = metrics) }
    }

    fun updatePatients(patients: List<DoctorPatient>) {
        _uiState.update {
            it.copy(
                patients = patients,
                criticalPatients = patients.filter { p -> p.currentState == "Crítico" }
            )
        }
    }

    fun selectPatient(patient: DoctorPatient?) {
        _uiState.update { it.copy(selectedPatient = patient) }
    }

    fun setLoadingState(isLoading: Boolean) {
        _uiState.update { it.copy(isLoadingPatients = isLoading) }
    }

    fun setErrorMessage(message: String?) {
        _uiState.update { it.copy(errorMessage = message) }
    }
}

