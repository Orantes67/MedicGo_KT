package com.TiololCode.medicgo.core.security

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "medicgo_prefs")

@Singleton
class TokenManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private object Keys {
        val TOKEN     = stringPreferencesKey("auth_token")
        val ROLE      = stringPreferencesKey("user_role")
        val USER_ID   = stringPreferencesKey("user_id")
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_EMAIL= stringPreferencesKey("user_email")
    }

    val tokenFlow: StateFlow<String?> = context.dataStore.data
        .map { it[Keys.TOKEN] }
        .stateIn(scope, SharingStarted.Eagerly, null)

    val roleFlow: StateFlow<String?> = context.dataStore.data
        .map { it[Keys.ROLE] }
        .stateIn(scope, SharingStarted.Eagerly, null)

    val userIdFlow: StateFlow<String?> = context.dataStore.data
        .map { prefs -> prefs[Keys.USER_ID] }
        .stateIn(scope, SharingStarted.Eagerly, null)

    val userNameFlow: StateFlow<String?> = context.dataStore.data
        .map { it[Keys.USER_NAME] }
        .stateIn(scope, SharingStarted.Eagerly, null)

    val userEmailFlow: StateFlow<String?> = context.dataStore.data
        .map { it[Keys.USER_EMAIL] }
        .stateIn(scope, SharingStarted.Eagerly, null)

    // --- Sync reads (non-blocking, read in-memory StateFlow value) ---
    fun getToken(): String?  = tokenFlow.value
    fun hasToken(): Boolean  = tokenFlow.value != null
    fun getUserRole(): String?  = roleFlow.value
    fun getUserId(): String?      = userIdFlow.value
    fun getUserName(): String?   = userNameFlow.value
    fun getUserEmail(): String?  = userEmailFlow.value

    // --- Suspend writes ---
    suspend fun saveToken(token: String) {
        context.dataStore.edit { it[Keys.TOKEN] = token }
    }

    suspend fun saveUserRole(role: String) {
        context.dataStore.edit { it[Keys.ROLE] = role }
    }

    suspend fun saveUserData(userId: String, userName: String, email: String) {
        context.dataStore.edit {
            it[Keys.USER_ID]    = userId
            it[Keys.USER_NAME]  = userName
            it[Keys.USER_EMAIL] = email
        }
    }

    suspend fun clearAllData() {
        context.dataStore.edit { it.clear() }
    }
}
