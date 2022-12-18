package nl.project.cocktailrecipes.ui.layouts

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import nl.project.cocktailrecipes.viewModel.CockTailViewModel

// contains code to like or dislike a recipe
object FavoriteIcon {

    @Composable
    fun Show(
        drinkIk: String,
        isLiked: Boolean,
        cockTailViewModel: CockTailViewModel
    ) {
        IconButton(
            onClick = {
                cockTailViewModel.likeDisLike(
                    drinkIk, !isLiked
                )
            }, colors = IconButtonDefaults.outlinedIconButtonColors(
                contentColor = Color.Yellow
            )
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = if (isLiked) Color.Magenta else Color.White
            )
        }
    }
}