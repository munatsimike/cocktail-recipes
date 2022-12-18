package nl.project.cocktailrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import nl.project.cocktailrecipes.ui.navigation.Destination
import nl.project.cocktailrecipes.ui.navigation.Navigation
import nl.project.cocktailrecipes.ui.navigation.TopBar
import nl.project.cocktailrecipes.ui.theme.CocktailRecipesTheme
import nl.project.cocktailrecipes.viewModel.CockTailViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CocktailRecipesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Start()
                }
            }
        }
    }

    @Composable
    fun Start(
        modifier: Modifier = Modifier, cockTailViewModel: CockTailViewModel = hiltViewModel()
    ) {
        val navController = rememberNavController()
        val backStatEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStatEntry?.destination?.route

        Scaffold(topBar = {
            AnimatedVisibility(
                visible = currentRoute != Destination.DetailPage.route,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                TopBar.Show(cockTailViewModel = cockTailViewModel)
            }
        }) { paddingValues ->
            Box(modifier.padding(paddingValues)) {
                Navigation.GetNavGraph(
                    controller = navController, cockTailViewModel = cockTailViewModel
                )
            }
        }
    }
}