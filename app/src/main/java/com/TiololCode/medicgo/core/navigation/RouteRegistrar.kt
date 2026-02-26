package com.TiololCode.medicgo.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

/**
 * Contract that every feature must implement to register its own composable
 * routes into the app's NavHost.
 *
 * Core never imports feature screens — features depend on core, not the reverse.
 * Each feature's data/di layer provides an implementation via Hilt @IntoSet.
 */
interface RouteRegistrar {
    fun NavGraphBuilder.register(navController: NavHostController)
}
