package nl.project.cocktailrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import nl.project.cocktailrecipes.ui.state.NetworkState
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
                    ShowCockTail()
                }
            }
        }
    }
}

@Composable
fun ShowCockTail(
    modifier: Modifier = Modifier, cockTailViewModel: CockTailViewModel = hiltViewModel()
) {
    val cocktails by cockTailViewModel.cockTails.collectAsState()
    Surface(
        modifier = modifier.padding(10.dp)
    ) {
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            when (cocktails) {
                is NetworkState.Error -> {}
                is NetworkState.Success -> {
                    val data = cocktails as NetworkState.Success
                    items(data.cockTails) { cocktail ->
                            Text(
                                modifier = Modifier.padding(5.dp),
                                text = cocktail.strCategory
                            )
                        }
                }
                NetworkState.Loading -> {
                    item {
                        CircularProgressIndicator()
                    }
                }
                NetworkState.NotLoading -> {}
            }
        }
    }
}