package com.TiololCode.medicgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.compose.AppTheme
<<<<<<< navigation
import com.TiololCode.medicgo.core.navigation.NavigationWrapper
import com.TiololCode.medicgo.core.navigation.RouteRegistrar
=======
import com.TiololCode.medicgo.features.doctor.presentation.screens.doctor.DoctorScreen
>>>>>>> develop
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var registrars: Set<@JvmSuppressWildcards RouteRegistrar>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
<<<<<<< navigation
                NavigationWrapper(registrars = registrars)
=======
                DoctorScreen(
                    onLogout = {
                        // Aquí irá la lógica de logout
                    }
                )
>>>>>>> develop
            }
        }
    }
}
