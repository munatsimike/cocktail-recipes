package nl.project.cocktailrecipes.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.project.cocktailrecipes.R
import nl.project.cocktailrecipes.ui.layouts.CockTailLayout
import nl.project.cocktailrecipes.ui.state.NetworkState
import nl.project.cocktailrecipes.viewModel.CockTailViewModel

// code to display home screen
object Main {

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun ShowCockTail(
        modifier: Modifier = Modifier,
        cockTailViewModel: CockTailViewModel,
        modalBottomSheetState: ModalBottomSheetState
    ) {
        val cocktails by cockTailViewModel.cockTails.collectAsState()
        val popularCocktails by cockTailViewModel.popularCockTail.collectAsState()
        val lazyState = rememberLazyGridState()

        val isFirstItemFullyVisible = remember {
            derivedStateOf {
                lazyState.firstVisibleItemIndex == 0
            }
        }

        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.primaryContainer,
            tonalElevation = 10.dp
        ) {
            Column {
                if (isFirstItemFullyVisible.value) {
                    Column(
                        modifier = modifier
                            .fillMaxHeight(0.26f)
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(top = 2.dp, bottom = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.popular),
                            modifier.padding(1.dp),
                            color = MaterialTheme.colorScheme.onSecondary,
                            fontSize = 18.sp
                        )
                        LazyHorizontalGrid(horizontalArrangement = Arrangement.spacedBy(1.dp),
                            rows = GridCells.Fixed(1),
                            content = {
                                when (popularCocktails) {
                                    is NetworkState.Error -> {}
                                    is NetworkState.Success -> {
                                        val data = popularCocktails as NetworkState.Success
                                        items(data.cockTails) { cocktail ->
                                            CockTailLayout.Show(
                                                cocktail = cocktail,
                                                cockTailViewModel,
                                                modalBottomSheetState,
                                                isPopular = true
                                            )
                                        }
                                    }
                                    else -> {}
                                }
                            })
                    }
                }
                LazyVerticalGrid(
                    modifier = modifier.padding(1.dp),
                    horizontalArrangement = Arrangement.spacedBy(1.dp),
                    verticalArrangement = Arrangement.spacedBy(1.dp),
                    columns = GridCells.Adaptive(100.dp),
                    state = lazyState
                ) {
                    when (cocktails) {
                        is NetworkState.Error -> {}
                        is NetworkState.Success -> {
                            val data = cocktails as NetworkState.Success
                            items(data.cockTails) { cocktail ->
                                CockTailLayout.Show(
                                    cocktail = cocktail,
                                    cockTailViewModel,
                                    modalBottomSheetState,
                                    isPopular = false
                                )
                            }
                        }
                        NetworkState.Loading -> {
                            item {
                                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                            }
                        }
                        NetworkState.NotLoading -> {}
                    }
                }
            }
        }
    }
}