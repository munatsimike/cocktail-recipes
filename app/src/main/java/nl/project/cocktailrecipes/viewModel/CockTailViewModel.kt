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


    private val _popularCockTail: MutableStateFlow<NetworkState> =
        MutableStateFlow(NetworkState.NotLoading)
    val popularCockTail: StateFlow<NetworkState> = _popularCockTail

    init {
        fetchCockTails()
        fetchPopularCockTails()
    }

    private suspend fun saveCockTailsToRoomDb() {
        cockTailRepository.saveCocktailsToRoom()
    }

    private fun fetchCockTails() {
        _cockTails.value = NetworkState.Loading
        viewModelScope.launch {
            saveCockTailsToRoomDb()
            cockTailRepository.cocktails.collectLatest {
                _cockTails.value = NetworkState.Success(it)
            }
        }
    }

    private fun fetchPopularCockTails() {
        viewModelScope.launch {
            _popularCockTail.value = NetworkState.Loading
            cockTailRepository.popular(ingredient = "c").collectLatest {
                _popularCockTail.value = NetworkState.Success(it)
            }
        }
    }

    fun searchCockTailById(id: String, isPopular: Boolean) {
        viewModelScope.launch {
            var data = cockTails
            if (isPopular) {
                data = popularCockTail
            }
            data.collectLatest { cockTails ->
                when (cockTails) {
                    is NetworkState.Success -> {
                        val data = cockTails.cockTails
                        _cockTail.value = CockTailState.Selected(data.first { it.idDrink == id })
                    }
                    else -> {}
                }
            }
        }
    }


    fun likeDisLike(id: String, isLiked: Boolean) {
        viewModelScope.launch {
            cockTailRepository.likeDislike(isLiked, id)
        }
    }
}
