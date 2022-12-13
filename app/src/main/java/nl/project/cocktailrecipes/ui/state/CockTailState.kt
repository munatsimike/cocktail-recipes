package nl.project.cocktailrecipes.ui.state

import nl.project.cocktailrecipes.model.CockTail

sealed class CockTailState {
    object NotLoading : CockTailState()
    object Loading : CockTailState()
    class MultipleDisplay(val cockTails: List<CockTail>) : CockTailState()
    class SingleDisplay(val cockTail: CockTail) : CockTailState()
    class Error(val error: String) : CockTailState()
}