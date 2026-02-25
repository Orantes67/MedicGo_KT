package com.TiololCode.medicgo.features.admistrator.presentation.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class AdministratorUiState(
    val selectedTab: AdminTab = AdminTab.METRICS
)

enum class AdminTab {
    METRICS, USERS, AREAS
}

@HiltViewModel
class AdministratorViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(AdministratorUiState())
    val uiState: StateFlow<AdministratorUiState> = _uiState.asStateFlow()

    fun onTabSelected(tab: AdminTab) {
        _uiState.update { it.copy(selectedTab = tab) }
    }
}

