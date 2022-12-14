package nl.project.cocktailrecipes.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import nl.project.cocktailrecipes.data.repository.CockTailRepository
import nl.project.cocktailrecipes.ui.state.CockTailState
import nl.project.cocktailrecipes.ui.state.NetworkState
import javax.inject.Inject

@HiltViewModel
class CockTailViewModel @Inject constructor(
    private val cockTailRepository: CockTailRepository
) : ViewModel() {

    private val _cockTails: MutableStateFlow<NetworkState> =
        MutableStateFlow(NetworkState.NotLoading)
    val cockTails: StateFlow<NetworkState> = _cockTails

    private val _cockTail: MutableStateFlow<CockTailState> =
        MutableStateFlow(CockTailState.NotSelected)
    val cockTail: StateFlow<CockTailState> = _cockTail

    init {
        viewModelScope.launch {
            saveCockTailsToRoomDb()
            fetchCockTails()
        }
    }

    private suspend fun saveCockTailsToRoomDb() {
        cockTailRepository.saveCocktailsToRoom()
    }

    private suspend fun fetchCockTails() {
        _cockTails.value = NetworkState.Loading
        cockTailRepository.cocktails.collect {
            _cockTails.value = NetworkState.Success(it)
        }
    }

    fun searchCockTailById(id: String) {
        viewModelScope.launch {
            cockTailRepository.searchCockTailById(id).collectLatest {
                _cockTail.value = CockTailState.NotSelected
                _cockTail.value = CockTailState.Selected(it)
            }
        }
    }

    fun likeDisLike(id: String, isLiked: Boolean) {
        viewModelScope.launch {
            cockTailRepository.likeDislike(isLiked, id)
        }
    }
}
