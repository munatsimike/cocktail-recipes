package nl.project.cocktailrecipes.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nl.project.cocktailrecipes.data.repository.CockTailRepository
import nl.project.cocktailrecipes.ui.state.NetworkState
import javax.inject.Inject

@HiltViewModel
class CockTailViewModel @Inject constructor(
    private val cockTailRepository: CockTailRepository
) : ViewModel() {

    private val _cockTails: MutableStateFlow<NetworkState> =
        MutableStateFlow(NetworkState.NotLoading)
    val cockTails: StateFlow<NetworkState> = _cockTails

    init {
        viewModelScope.launch {
            saveCockTailsToRoomDb()
            fetchCockTails()
        }
    }

    suspend fun saveCockTailsToRoomDb() {
        cockTailRepository.saveCocktailsToRoom()
    }

    private suspend fun fetchCockTails() {
        _cockTails.value = NetworkState.Loading
        cockTailRepository.cocktails.collect {
            _cockTails.value = NetworkState.Success(it)
        }
    }
}
