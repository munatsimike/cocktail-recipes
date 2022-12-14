package nl.project.cocktailrecipes.ui.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import nl.project.cocktailrecipes.model.CockTail
import nl.project.cocktailrecipes.viewModel.CockTailViewModel

object CockTailLayout {

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun Show(
        cocktail: CockTail,
        cockTailViewModel: CockTailViewModel,
        modalSheetState: ModalBottomSheetState,
        modifier: Modifier = Modifier
    ) {

        val scope = rememberCoroutineScope()
        OutlinedCard(
            modifier = modifier.size(150.dp),
        ) {
            Box(contentAlignment = Alignment.BottomStart) {
                ImageViewer.Layout(url = cocktail.strDrinkThumb) {

                scope.launch {
                    cockTailViewModel.searchCockTailById(id = cocktail.idDrink)
                        modalSheetState.show()
                    }
                }
                Text(
                    text = cocktail.strDrink,
                    color = MaterialTheme.colorScheme.background,
                    fontSize = 18.sp,
                    modifier = modifier
                        .alpha(0.7f)
                        .background(
                            color = MaterialTheme.colorScheme.onBackground,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(5.dp)
                )
                Box(
                    modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd
                ) {
                    Row(
                        modifier = modifier
                            .alpha(0.7f)
                            .background(
                                color = MaterialTheme.colorScheme.onBackground,
                                shape = RoundedCornerShape(5.dp)
                            )
                    ) {
                        IconButton(
                            onClick = {
                                cockTailViewModel.likeDisLike(
                                    cocktail.idDrink,
                                    !cocktail.IsLiked
                                )
                            }, colors = IconButtonDefaults.outlinedIconButtonColors(
                                contentColor = Color.Yellow
                            )

                        ) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null,
                                tint = if (cocktail.IsLiked) Color.Red else Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}