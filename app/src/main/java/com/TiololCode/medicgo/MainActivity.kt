package com.TiololCode.medicgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.compose.AppTheme
import com.TiololCode.medicgo.features.doctor.presentation.screens.doctor.DoctorScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                DoctorScreen(
                    onLogout = {
                        // Aquí irá la lógica de logout
                    }
                )
            }
        }
    }
}
