package com.TiololCode.medicgo.features.enfermero.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.TiololCode.medicgo.core.security.TokenManager
import com.TiololCode.medicgo.features.enfermero.domain.entities.EnfermeroMetric
import com.TiololCode.medicgo.features.enfermero.domain.entities.EnfermeroPatient
import com.TiololCode.medicgo.features.enfermero.domain.usescases.AddNotaRapidaUseCase
import com.TiololCode.medicgo.features.enfermero.domain.usescases.GetMisPacientesUseCase
import com.TiololCode.medicgo.features.enfermero.domain.usescases.UpdatePacienteEstadoUseCase
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

data class EnfermeroUiState(
    val nurseName: String = "",
    val metrics: EnfermeroMetric = EnfermeroMetric(0, 0, 0, 0),
    val patients: List<EnfermeroPatient> = emptyList(),
    val isLoading: Boolean = false,
    val selectedPatientId: Long? = null,
    val selectedState: String = "",
    val notaRapida: String = "",
    val isUpdating: Boolean = false,
    val errorMessage: String? = null
)

sealed interface EnfermeroEvent {
    object EstadoActualizado : EnfermeroEvent
    object NotaAgregada : EnfermeroEvent
    data class Error(val message: String) : EnfermeroEvent
}

@HiltViewModel
class EnfermeroViewModel @Inject constructor(
    private val getMisPacientesUseCase: GetMisPacientesUseCase,
    private val updatePacienteEstadoUseCase: UpdatePacienteEstadoUseCase,
    private val addNotaRapidaUseCase: AddNotaRapidaUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(EnfermeroUiState())
    val uiState: StateFlow<EnfermeroUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<EnfermeroEvent>()
    val events: SharedFlow<EnfermeroEvent> = _events.asSharedFlow()

    init {
        _uiState.update { it.copy(nurseName = tokenManager.getUserName() ?: "") }
        loadMisPacientes()
    }

    fun loadMisPacientes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            getMisPacientesUseCase().fold(
                onSuccess = { result ->
                    _uiState.update { it.copy(
                        isLoading = false,
                        metrics = result.metrics,
                        patients = result.patients
                    ) }
                },
                onFailure = { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message) }
                    _events.emit(EnfermeroEvent.Error(e.message ?: "Error al cargar pacientes"))
                }
            )
        }
    }

    fun selectPatient(patient: EnfermeroPatient?) {
        _uiState.update { it.copy(
            selectedPatientId = patient?.id,
            selectedState = patient?.currentState ?: "",
            notaRapida = ""
        ) }
    }

    fun onStateSelect(state: String) {
        _uiState.update { it.copy(selectedState = state) }
    }

    fun onNotaChange(text: String) {
        _uiState.update { it.copy(notaRapida = text) }
    }

    fun confirmUpdate() {
        val currentState = _uiState.value
        val patientId = currentState.selectedPatientId ?: return
        val newState = currentState.selectedState
        val nota = currentState.notaRapida

        // Guardar estado anterior para rollback
        val previousState = currentState.patients
            .firstOrNull { it.id == patientId }
            ?.currentState ?: return

        viewModelScope.launch {
            // Actualización optimista: refleja el cambio en la UI antes de esperar al servidor
            _uiState.update { s ->
                s.copy(
                    isUpdating = true,
                    patients = s.patients.map { p ->
                        if (p.id == patientId) p.copy(currentState = newState) else p
                    }
                )
            }

            updatePacienteEstadoUseCase(patientId, newState).fold(
                onSuccess = {
                    _events.emit(EnfermeroEvent.EstadoActualizado)
                },
                onFailure = { e ->
                    // Rollback: revertir al estado anterior si el servidor rechaza el cambio
                    _uiState.update { s ->
                        s.copy(
                            patients = s.patients.map { p ->
                                if (p.id == patientId) p.copy(currentState = previousState) else p
                            },
                            selectedState = previousState
                        )
                    }
                    _events.emit(EnfermeroEvent.Error(e.message ?: "Error al actualizar estado"))
                }
            )

            if (nota.isNotBlank()) {
                addNotaRapidaUseCase(patientId, nota).fold(
                    onSuccess = { _events.emit(EnfermeroEvent.NotaAgregada) },
                    onFailure = { e ->
                        _events.emit(EnfermeroEvent.Error(e.message ?: "Error al agregar nota"))
                    }
                )
            }

            _uiState.update { it.copy(isUpdating = false, selectedPatientId = null, notaRapida = "") }
        }
    }
}
