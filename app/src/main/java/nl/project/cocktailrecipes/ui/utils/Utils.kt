package nl.project.cocktailrecipes.ui.utils

import androidx.navigation.NavHostController

// contains code to navigate to any destination
object Utils {
    fun navigateTo(destination: String, navHostController: NavHostController) {
        navHostController.navigate(destination) {
            navHostController.graph.startDestinationRoute?.let {
                popUpTo(it) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}