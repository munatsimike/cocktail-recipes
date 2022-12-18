package nl.project.cocktailrecipes.ui.navigation

sealed class Destination(val route: String) {
    object DetailPage : Destination(route = "detailPage")
    object Home : Destination(route = "home")
}
