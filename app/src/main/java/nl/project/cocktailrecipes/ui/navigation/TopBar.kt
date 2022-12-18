package nl.project.cocktailrecipes.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.project.cocktailrecipes.R
import nl.project.cocktailrecipes.viewModel.CockTailViewModel

object TopBar {

    @Composable
    fun Show(cockTailViewModel: CockTailViewModel, modifier: Modifier = Modifier) {
        var searchQuery by remember { mutableStateOf("") }

        TopAppBar(backgroundColor = MaterialTheme.colorScheme.surface) {
            Text(
                text = stringResource(id = R.string.cock_tail_recipe),
                modifier = modifier.padding(5.dp),
                fontSize = 20.sp
            )
            TextField(value = searchQuery, onValueChange = { newText ->
                searchQuery = newText.trim().take(1)
                var text = searchQuery
                if (searchQuery.isEmpty()) {
                    text = "a"
                }
                cockTailViewModel.saveCockTailsToRoomDb(searchQuery = text)
            }, leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = null
                )
            })
        }
    }
}