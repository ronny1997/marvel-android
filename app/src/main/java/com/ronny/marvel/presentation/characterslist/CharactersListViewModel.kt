package com.ronny.marvel.presentation.characterslist

import androidx.lifecycle.viewModelScope
import com.ronny.marvel.common.ui.BaseViewModel
import com.ronny.marvel.common.util.Resource
import com.ronny.marvel.domain.use_case.GetCharactersUseCase
import com.ronny.marvel.presentation.model.CharacterItemView
import com.ronny.marvel.presentation.model.CharactersListView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : BaseViewModel() {
    private val _charactersUiState = MutableStateFlow(CharactersListUiState())
    val charactersUiState: StateFlow<CharactersListUiState> get() =  _charactersUiState

    val lastVisibility = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            lastVisibility.collect {
                getCharactersList(it)
            }
        }
    }

    private suspend fun getCharactersList(offset: Int = 0) {
        getCharactersUseCase(GetCharactersUseCase.Params(offset)).onStart {
            _charactersUiState.value = CharactersListUiState(isLoading = true)
        }.collect {
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
        }
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