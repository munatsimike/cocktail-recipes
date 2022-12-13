package nl.project.cocktailrecipes.ui.layouts

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import nl.project.cocktailrecipes.R

// code to display image

object ImageViewer {

    @Composable
    fun Layout(url: String, modifier: Modifier = Modifier, onImageClick: ()-> Unit) {
        AsyncImage(
            modifier = modifier.clickable( enabled = true, onClick = {
                onImageClick.invoke()
            }),
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),

            contentDescription = stringResource(
                id = R.string.cock_tail_image
            ),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_baseline_image_24)
        )
    }
}