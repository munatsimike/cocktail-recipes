package nl.project.cocktailrecipes.ui.state

import nl.project.cocktailrecipes.model.CockTail

sealed class CockTailState{
    object NotSelected: CockTailState()
    class Selected(val cockTail: CockTail): CockTailState()
}
