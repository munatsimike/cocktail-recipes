package nl.project.cocktailrecipes.ui.state

import nl.project.cocktailrecipes.model.CockTail

sealed class NetworkState {
    object NotLoading : NetworkState()
    object Loading : NetworkState()
    class Success(val cockTails: List<CockTail>) : NetworkState()
    class Error(val error: String) : NetworkState()
}