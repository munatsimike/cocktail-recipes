package nl.project.cocktailrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import nl.project.cocktailrecipes.ui.screens.DetailPage
import nl.project.cocktailrecipes.ui.screens.Main.ShowCockTail
import nl.project.cocktailrecipes.ui.state.CockTailState
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
                    Start()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun Start(
        cockTailViewModel: CockTailViewModel = hiltViewModel(), modifier: Modifier = Modifier
    ) {
        val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val cockTail by cockTailViewModel.cockTail.collectAsState()

        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetContent = {
                when (cockTail) {
                    is CockTailState.Selected -> {
                        val data = cockTail as CockTailState.Selected
                        Box(
                            modifier = modifier
                                .fillMaxHeight(0.75f)
                                .fillMaxWidth()
                        ) {
                            DetailPage.Show(cockTail = data.cockTail)
                        }
                    }

                    is CockTailState.NotSelected -> {
                        Text(text = "")
                    }
                }
            },
            sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
            sheetBackgroundColor = Color.Yellow
        ) {
            Scaffold(topBar = {
                TopAppBar(backgroundColor = MaterialTheme.colorScheme.surface) {
                    Text(text = stringResource(id = R.string.cock_tail_recipe), modifier = modifier.padding(5.dp), fontSize = 20.sp)
                }

            }) { paddingValues ->
                Box(modifier.padding(paddingValues)) {
                    ShowCockTail(
                        cockTailViewModel = cockTailViewModel, modalBottomSheetState = sheetState
                    )
                }
            }
        }
    }
}