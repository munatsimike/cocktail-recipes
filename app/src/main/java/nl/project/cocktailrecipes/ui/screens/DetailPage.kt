package nl.project.cocktailrecipes.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.project.cocktailrecipes.R
import nl.project.cocktailrecipes.model.CockTail
import nl.project.cocktailrecipes.ui.layouts.FavoriteIcon
import nl.project.cocktailrecipes.ui.layouts.ImageViewer
import nl.project.cocktailrecipes.ui.state.CockTailState
import nl.project.cocktailrecipes.viewModel.CockTailViewModel

// contains code to show detail page
object DetailPage {
    private val fontSize18 = 18.sp

    @Composable
    fun Show(
        cockTailViewModel: CockTailViewModel, modifier: Modifier = Modifier
    ) {
        val cockTailState by cockTailViewModel.cockTail.collectAsState()
        when (cockTailState) {
            CockTailState.NotSelected -> {}
            is CockTailState.Selected -> {
                val cockTail = (cockTailState as CockTailState.Selected).cockTail

                Box(
                    modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter
                ) {

                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.45f)
                            .background(MaterialTheme.colorScheme.inversePrimary)
                            .clip(
                                shape = CutCornerShape(
                                    bottomEndPercent = 20,
                                    bottomStartPercent = 20
                                )
                            )

                    ) {
                        // show image
                        ImageViewer.Layout(url = cockTail.strDrinkThumb) {}
                    }
                    Box(
                        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.55f)
                                .verticalScroll(rememberScrollState())
                                .background(MaterialTheme.colorScheme.inversePrimary)
                                .padding(15.dp)


                        ) {
                            // show Recipe
                            ShowRecipe(cockTail = cockTail)
                        }
                    }

                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxSize(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Column() {
                            ShowShareIcon(onShare = {})
                            Spacer(modifier = Modifier.height(20.dp))
                            FavoriteIcon.Show(
                                drinkIk = cockTail.idDrink,
                                isLiked = cockTail.IsLiked,
                                cockTailViewModel = cockTailViewModel
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ShowRecipe(cockTail: CockTail) {
        Text(
            text = cockTail.strDrink, fontSize = 22.sp, fontWeight = FontWeight.Bold
        )
        Column {

            Text(
                text = stringResource(id = R.string.ingredients),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = cockTail.strIngredient1, fontSize = fontSize18)
            Text(text = cockTail.strIngredient2, fontSize = fontSize18)
            cockTail.strIngredient3?.let {
                Text(
                    text = it, fontSize = fontSize18
                )
            }
            cockTail.strIngredient4?.let {
                Text(
                    text = it, fontSize = fontSize18
                )
            }
        }

        Column {
            Text(
                text = stringResource(id = R.string.method),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = cockTail.strInstructions, fontSize = fontSize18, overflow = TextOverflow.Clip
            )
        }
    }

    @Composable
    fun ShowShareIcon(modifier: Modifier = Modifier, onShare: () -> Unit) {
        IconButton(onClick = { onShare.invoke() }) {
            Icon(
                modifier = modifier.size(20.dp),
                imageVector = Icons.Default.Share,
                contentDescription = null,
                tint = Color.Magenta
            )
        }
    }
}
