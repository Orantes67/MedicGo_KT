package com.TiololCode.medicgo.features.admistrator.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.TiololCode.medicgo.core.security.TokenManager
import com.TiololCode.medicgo.features.admistrator.domain.usescases.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AdministratorUiState(
    val selectedTab: AdminTab = AdminTab.METRICS,
    val isLoggingOut: Boolean = false,
    val logoutError: String? = null
)

enum class AdminTab {
    METRICS, USERS, AREAS
}

@HiltViewModel
class AdministratorViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(AdministratorUiState())
    val uiState: StateFlow<AdministratorUiState> = _uiState.asStateFlow()

    fun onTabSelected(tab: AdminTab) {
        _uiState.update { it.copy(selectedTab = tab) }
    }

    fun onLogout(onLogoutSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoggingOut = true, logoutError = null) }
            val result = logoutUseCase()
            result.fold(
                onSuccess = {
                    _uiState.update { it.copy(isLoggingOut = false) }
                    onLogoutSuccess()
                },
                onFailure = { error ->
                    _uiState.update { it.copy(isLoggingOut = false, logoutError = error.message) }
                    tokenManager.clearAllData()
                    onLogoutSuccess()
                }
            )
        }
    }
}


