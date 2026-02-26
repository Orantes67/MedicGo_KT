package com.TiololCode.medicgo.core.security

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE
    )

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }

    fun clearToken() {
        sharedPreferences.edit().remove(KEY_TOKEN).apply()
    }

    fun hasToken(): Boolean {
        return getToken() != null
    }

    fun saveUserRole(role: String) {
        sharedPreferences.edit().putString(KEY_ROLE, role).apply()
    }

    fun getUserRole(): String? {
        return sharedPreferences.getString(KEY_ROLE, null)
    }

    fun saveUserData(userId: Long, userName: String, email: String) {
        sharedPreferences.edit().apply {
            putLong(KEY_USER_ID, userId)
            putString(KEY_USER_NAME, userName)
            putString(KEY_USER_EMAIL, email)
        }.apply()
    }

    fun getUserId(): Long? {
        val id = sharedPreferences.getLong(KEY_USER_ID, -1L)
        return if (id == -1L) null else id
    }

    fun getUserName(): String? {
        return sharedPreferences.getString(KEY_USER_NAME, null)
    }

    fun getUserEmail(): String? {
        return sharedPreferences.getString(KEY_USER_EMAIL, null)
    }

    fun clearAllData() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        private const val PREFS_NAME = "medicgo_prefs"
        private const val KEY_TOKEN = "auth_token"
        private const val KEY_ROLE = "user_role"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_USER_EMAIL = "user_email"
    }
}

