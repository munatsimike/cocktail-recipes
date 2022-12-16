package nl.project.cocktailrecipes.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.project.cocktailrecipes.R
import nl.project.cocktailrecipes.model.CockTail
import nl.project.cocktailrecipes.ui.layouts.ImageViewer

object DetailPage {

    @Composable
    fun Show(
        cockTail: CockTail, modifier: Modifier = Modifier
    ) {
        ImageViewer.Layout(url = cockTail.strDrinkThumb) {}
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .alpha(0.6f)
                .background(Color.White)
                .padding(15.dp)

        ) {
            Text(text = cockTail.strDrink, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Column {
                Text(
                    text = stringResource(id = R.string.ingredients),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = cockTail.strIngredient1, fontSize = 18.sp)
                Text(text = cockTail.strIngredient2, fontSize = 18.sp)
                cockTail.strIngredient3?.let { Text(text = it, fontSize = 18.sp) }
                cockTail.strIngredient4?.let { Text(text = it, fontSize = 18.sp) }
            }

            Column {
                Text(
                    text = stringResource(id = R.string.method),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = cockTail.strInstructions,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Clip
                )
            }

            Spacer(modifier = modifier.weight(1f))
        }
    }
}

