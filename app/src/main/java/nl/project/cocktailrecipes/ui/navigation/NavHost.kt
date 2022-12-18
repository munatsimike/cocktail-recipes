package nl.project.cocktailrecipes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import nl.project.cocktailrecipes.ui.screens.DetailPage
import nl.project.cocktailrecipes.ui.screens.Main
import nl.project.cocktailrecipes.viewModel.CockTailViewModel

object Navigation {

    @Composable
    fun GetNavGraph(
        controller: NavHostController,
        cockTailViewModel: CockTailViewModel,
    ) {
        NavHost(navController = controller, startDestination = Destination.Home.route, builder = {
            composable(route = Destination.Home.route) {
                Main.ShowCockTail(
                    cockTailViewModel = cockTailViewModel, navController = controller
                )
            }

            composable(route = Destination.DetailPage.route) {
                DetailPage.Show(cockTailViewModel = cockTailViewModel)
            }
        })
    }
}