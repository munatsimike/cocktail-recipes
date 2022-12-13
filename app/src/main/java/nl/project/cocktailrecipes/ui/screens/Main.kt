package nl.project.cocktailrecipes.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import nl.project.cocktailrecipes.ui.layouts.CockTailLayout
import nl.project.cocktailrecipes.ui.state.CockTailState
import nl.project.cocktailrecipes.viewModel.CockTailViewModel

// code to display home screen
object Main {

    @Composable
    fun ShowCockTail(
        modifier: Modifier = Modifier, cockTailViewModel: CockTailViewModel = hiltViewModel()
    ) {
        val cocktails by cockTailViewModel.cockTails.collectAsState()
        Surface(
            modifier = modifier, color = MaterialTheme.colorScheme.primary, tonalElevation = 10.dp
        ) {
            LazyVerticalGrid(
                modifier = modifier.padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                columns = GridCells.Adaptive(130.dp)
            ) {
                when (cocktails) {
                    is CockTailState.Error -> {}
                    is CockTailState.MultipleDisplay -> {
                        val data = cocktails as CockTailState.MultipleDisplay
                        items(data.cockTails) { cocktail ->
                            CockTailLayout.Show(cocktail = cocktail, cockTailViewModel)
                        }
                    }
                    CockTailState.Loading -> {
                        item {
                            CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                        }
                    }
                    CockTailState.NotLoading -> {}
                    else -> {}
                }
            }
        }
    }
}