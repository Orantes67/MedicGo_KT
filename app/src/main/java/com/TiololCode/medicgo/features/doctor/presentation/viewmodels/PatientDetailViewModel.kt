package com.TiololCode.medicgo.features.doctor.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.TiololCode.medicgo.features.doctor.domain.entities.PatientDetail
import com.TiololCode.medicgo.features.doctor.domain.entities.PatientNote
import com.TiololCode.medicgo.features.doctor.domain.usescases.AddPatientNoteUseCase
import com.TiololCode.medicgo.features.doctor.domain.usescases.GetPatientDetailUseCase
import com.TiololCode.medicgo.features.doctor.domain.usescases.UpdatePatientStateUseCase
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

data class PatientDetailUiState(
    val detail: PatientDetail? = null,
    val isLoading: Boolean = false,
    val isUpdatingState: Boolean = false,
    val isAddingNote: Boolean = false,
    val selectedState: String = "",
    val newNoteContent: String = "",
    val errorMessage: String? = null
)

sealed interface PatientDetailEvent {
    object StateUpdated : PatientDetailEvent
    object NoteAdded   : PatientDetailEvent
    data class Error(val message: String) : PatientDetailEvent
}

@HiltViewModel
class PatientDetailViewModel @Inject constructor(
    private val getPatientDetailUseCase: GetPatientDetailUseCase,
    private val updatePatientStateUseCase: UpdatePatientStateUseCase,
    private val addPatientNoteUseCase: AddPatientNoteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PatientDetailUiState())
    val uiState: StateFlow<PatientDetailUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<PatientDetailEvent>()
    val events: SharedFlow<PatientDetailEvent> = _events.asSharedFlow()

    fun loadDetail(patientId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            getPatientDetailUseCase(patientId).fold(
                onSuccess = { detail ->
                    _uiState.update {
                        it.copy(
                            isLoading     = false,
                            detail        = detail,
                            selectedState = detail.currentState
                        )
                    }
                },
                onFailure = { error ->
                    val msg = error.message ?: "Error al cargar el paciente"
                    _uiState.update { it.copy(isLoading = false, errorMessage = msg) }
                    _events.emit(PatientDetailEvent.Error(msg))
                }
            )
        }
    }

    fun onStateSelect(state: String) {
        _uiState.update { it.copy(selectedState = state) }
    }

    fun confirmStateUpdate() {
        val current = _uiState.value
        val newState = current.selectedState
        val previousState = current.detail?.currentState ?: return
        viewModelScope.launch {
            // Optimistic update
            _uiState.update {
                it.copy(
                    isUpdatingState = true,
                    detail = it.detail?.copy(currentState = newState)
                )
            }
            updatePatientStateUseCase(current.detail!!.id, newState).fold(
                onSuccess = {
                    _uiState.update { it.copy(isUpdatingState = false) }
                    _events.emit(PatientDetailEvent.StateUpdated)
                },
                onFailure = { error ->
                    // Rollback
                    _uiState.update {
                        it.copy(
                            isUpdatingState = false,
                            selectedState   = previousState,
                            detail          = it.detail?.copy(currentState = previousState)
                        )
                    }
                    _events.emit(PatientDetailEvent.Error(error.message ?: "Error al actualizar estado"))
                }
            )
        }
    }

    fun onNoteContentChange(content: String) {
        _uiState.update { it.copy(newNoteContent = content) }
    }

    fun submitNote() {
        val current = _uiState.value
        val patientId = current.detail?.id ?: return
        val content = current.newNoteContent
        val tempNote = PatientNote(id = -1L, author = "...", date = "", content = content)
        val previousNotes = current.detail.clinicalNotes
        viewModelScope.launch {
            // Optimistic update
            _uiState.update {
                it.copy(
                    isAddingNote  = true,
                    newNoteContent = "",
                    detail = it.detail?.copy(clinicalNotes = it.detail.clinicalNotes + tempNote)
                )
            }
            addPatientNoteUseCase(patientId, content).fold(
                onSuccess = { realNote ->
                    _uiState.update {
                        it.copy(
                            isAddingNote = false,
                            detail = it.detail?.copy(
                                clinicalNotes = it.detail.clinicalNotes
                                    .filter { n -> n.id != -1L } + realNote
                            )
                        )
                    }
                    _events.emit(PatientDetailEvent.NoteAdded)
                },
                onFailure = { error ->
                    // Rollback
                    _uiState.update {
                        it.copy(
                            isAddingNote   = false,
                            newNoteContent = content,
                            detail         = it.detail?.copy(clinicalNotes = previousNotes)
                        )
                    }
                    _events.emit(PatientDetailEvent.Error(error.message ?: "Error al agregar nota"))
                }
            )
        }
    }
}
