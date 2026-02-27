package com.TiololCode.medicgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.compose.AppTheme
import com.TiololCode.medicgo.core.navigation.NavigationWrapper
import com.TiololCode.medicgo.core.navigation.RouteRegistrar
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
                NavigationWrapper(registrars = registrars)
            }
        }
    }
}

