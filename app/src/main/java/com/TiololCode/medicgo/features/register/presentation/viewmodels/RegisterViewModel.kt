package com.TiololCode.medicgo.features.register.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.TiololCode.medicgo.features.register.domain.entities.RegisterResult
import com.TiololCode.medicgo.features.register.domain.usescases.PosRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RegisterUiState(
    val name: String = "",
    val licenseNumber: String = "",
    val specialty: String = "",
    val email: String = "",
    val password: String = "",
    val specialtyExpanded: Boolean = false,
    val isLoading: Boolean = false,
    val registerResult: RegisterResult? = null,
    val error: String? = null
)

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val posRegisterUseCase: PosRegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onNameChange(value: String) {
        _uiState.update { it.copy(name = value, error = null) }
    }

    fun onLicenseNumberChange(value: String) {
        _uiState.update { it.copy(licenseNumber = value, error = null) }
    }

    fun onSpecialtyChange(value: String) {
        _uiState.update { it.copy(specialty = value, specialtyExpanded = false, error = null) }
    }

    fun onSpecialtyExpandedChange(expanded: Boolean) {
        _uiState.update { it.copy(specialtyExpanded = expanded) }
    }

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value, error = null) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value, error = null) }
    }

    fun onRegister() {
        val state = _uiState.value
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = posRegisterUseCase(
                name = state.name,
                licenseNumber = state.licenseNumber,
                specialty = state.specialty,
                email = state.email,
                password = state.password
            )
            result.fold(
                onSuccess = { registerResult ->
                    _uiState.update { it.copy(isLoading = false, registerResult = registerResult) }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(isLoading = false, error = error.message ?: "Error desconocido")
                    }
                }
            )
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    fun clearRegisterResult() {
        _uiState.update { it.copy(registerResult = null) }
    }
}

