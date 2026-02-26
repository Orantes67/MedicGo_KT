package com.TiololCode.medicgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.compose.AppTheme
<<<<<<< Updated upstream
import com.TiololCode.medicgo.features.admistrator.presentation.screens.administrator.AdministratorScreen
=======
import com.TiololCode.medicgo.core.navigation.NavigationWrapper
import com.TiololCode.medicgo.core.navigation.RouteRegistrar
>>>>>>> Stashed changes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
<<<<<<< Updated upstream
                AdministratorScreen()
=======
                NavigationWrapper(registrars = registrars)
>>>>>>> Stashed changes
            }
        }
    }
}
