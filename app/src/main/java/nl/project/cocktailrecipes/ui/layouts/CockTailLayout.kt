package nl.project.cocktailrecipes.ui.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import nl.project.cocktailrecipes.model.CockTail
import nl.project.cocktailrecipes.ui.navigation.Destination
import nl.project.cocktailrecipes.ui.utils.Utils.navigateTo
import nl.project.cocktailrecipes.viewModel.CockTailViewModel

// contains code to display cocktail layout
object CockTailLayout {

    @Composable
    fun Show(
        cocktail: CockTail,
        cockTailViewModel: CockTailViewModel,
        navHostController: NavHostController,
        modifier: Modifier = Modifier,
        isPopular: Boolean
    ) {
        OutlinedCard(
            modifier = modifier.size(120.dp), shape = RectangleShape
        ) {
            Box(contentAlignment = Alignment.BottomStart) {
                // show cocktail image
                ImageViewer.Layout(url = cocktail.strDrinkThumb) {
                    cockTailViewModel.searchCockTailById(
                        id = cocktail.idDrink, isPopular = isPopular
                    )
                    navigateTo(
                        destination = Destination.DetailPage.route,
                        navHostController = navHostController
                    )
                }
                // show cocktail name
                Text(
                    text = cocktail.strDrink,
                    color = MaterialTheme.colorScheme.background,
                    fontSize = 14.sp,
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
                        // show favourite icon
                        FavoriteIcon.Show(
                            drinkIk = cocktail.idDrink,
                            isLiked = cocktail.IsLiked,
                            cockTailViewModel = cockTailViewModel
                        )
                    }
                }
            }
        }
    }
}