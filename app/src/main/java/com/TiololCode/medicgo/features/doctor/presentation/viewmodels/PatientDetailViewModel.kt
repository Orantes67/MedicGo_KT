package com.TiololCode.medicgo.features.doctor.presentation.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.TiololCode.medicgo.features.doctor.domain.entities.PatientNote
import javax.inject.Inject

data class PatientDetailUiState(
    val patientNotes: List<PatientNote> = emptyList(),
    val newNoteContent: String = "",
    val newPatientState: String = "",
    val isAddingNote: Boolean = false,
    val isUpdatingState: Boolean = false
)

@HiltViewModel
class PatientDetailViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(PatientDetailUiState())
    val uiState: StateFlow<PatientDetailUiState> = _uiState.asStateFlow()

    fun updateNoteContent(content: String) {
        _uiState.update { it.copy(newNoteContent = content) }
    }

    fun updatePatientState(state: String) {
        _uiState.update { it.copy(newPatientState = state) }
    }

    fun loadPatientNotes(notes: List<PatientNote>) {
        _uiState.update { it.copy(patientNotes = notes) }
    }

    fun setAddingNoteState(isAdding: Boolean) {
        _uiState.update { it.copy(isAddingNote = isAdding) }
    }

    fun setUpdatingStateStatus(isUpdating: Boolean) {
        _uiState.update { it.copy(isUpdatingState = isUpdating) }
    }

    fun clearNewNoteContent() {
        _uiState.update { it.copy(newNoteContent = "") }
    }
}

