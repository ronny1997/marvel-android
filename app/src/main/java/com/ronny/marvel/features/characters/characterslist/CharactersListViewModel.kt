package com.ronny.marvel.features.characters.characterslist

import androidx.lifecycle.viewModelScope
import com.ronny.marvel.core.common.Resource
import com.ronny.marvel.core.exception.Failure
import com.ronny.marvel.core.platform.BaseViewModel
import com.ronny.marvel.features.characters.characterslist.usecase.GetCharactersUseCase
import com.ronny.marvel.features.characters.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : BaseViewModel() {
    private val _charactersUiState = MutableStateFlow(CharactersListUiState())
    val charactersUiState: StateFlow<CharactersListUiState> = _charactersUiState

    val lastVisibility = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            lastVisibility.collect {
                getCharactersList(it)
            }
        }
    }

    private fun getCharactersList(offset: Int = 0) {
        getCharactersUseCase(GetCharactersUseCase.Params(offset)).onStart {
            _charactersUiState.value = CharactersListUiState(isLoading = true)
        }.onEach {
            when (it) {
                is Resource.Loading -> _charactersUiState.value =
                    CharactersListUiState(isLoading = true)
                is Resource.Error -> _charactersUiState.value =
                    CharactersListUiState(error = handleFailure(it.error))
                is Resource.Success -> {
                    _charactersUiState.value =
                        CharactersListUiState(charactersListView = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
    fun goToCharacterDetail(character: CharacterItemView) {
        navigate(CharactersListFragmentDirections.goToCharacterDetailFragment(character))
    }

}

data class CharactersListUiState(
    val charactersListView: CharactersListView? = null,
    val error: String = "",
    val isLoading: Boolean = false
)