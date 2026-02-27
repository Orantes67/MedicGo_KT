package com.TiololCode.medicgo.features.doctor.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.TiololCode.medicgo.core.security.TokenManager
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorMetric
import com.TiololCode.medicgo.features.doctor.domain.entities.DoctorPatient
import com.TiololCode.medicgo.features.doctor.domain.usescases.GetMyPatientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DoctorUiState(
    val doctorName: String = "",
    val metrics: DoctorMetric = DoctorMetric(0, 0, 0, 0),
    val patients: List<DoctorPatient> = emptyList(),
    val criticalPatients: List<DoctorPatient> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

sealed interface DoctorEvent {
    data class Error(val message: String) : DoctorEvent
}

@HiltViewModel
class DoctorViewModel @Inject constructor(
    private val getMyPatientsUseCase: GetMyPatientsUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(DoctorUiState())
    val uiState: StateFlow<DoctorUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<DoctorEvent>()
    val events: SharedFlow<DoctorEvent> = _events.asSharedFlow()

    init {
        _uiState.update { it.copy(doctorName = tokenManager.getUserName() ?: "") }
        loadMyPatients()
    }

    fun loadMyPatients() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            getMyPatientsUseCase().fold(
                onSuccess = { result ->
                    _uiState.update {
                        it.copy(
                            isLoading       = false,
                            metrics         = result.metrics,
                            patients        = result.patients,
                            criticalPatients = result.criticalPatients
                        )
                    }
                },
                onFailure = { error ->
                    val msg = error.message ?: "Error al cargar los pacientes"
                    _uiState.update { it.copy(isLoading = false, errorMessage = msg) }
                    _events.emit(DoctorEvent.Error(msg))
                }
            )
        }
    }
}
