package com.TiololCode.medicgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.compose.AppTheme
import com.TiololCode.medicgo.features.login.presentation.screens.LoginScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                LoginScreen(
                    onNavigateToRegister = {
                        // TODO: navegar a pantalla de registro
                    },
                    onLoginSuccess = {
                        // TODO: navegar a pantalla principal
                    }
                )
            }
        }
    }
}
