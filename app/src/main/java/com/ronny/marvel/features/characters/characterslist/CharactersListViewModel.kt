package com.ronny.marvel.features.characters.characterslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ronny.marvel.core.common.Resource
import com.ronny.marvel.core.exception.Failure
import com.ronny.marvel.core.interactor.None
import com.ronny.marvel.core.platform.BaseViewModel
import com.ronny.marvel.core.util.Event
import com.ronny.marvel.features.characters.characterslist.usecase.GetCharacters
import com.ronny.marvel.features.characters.model.CharactersListDto
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersListViewModel @Inject constructor(
    private val getCharacters: GetCharacters
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
        getCharacters(GetCharacters.Params(offset)).onStart {
            _charactersUiState.value = CharactersListUiState(isLoading = true)
        }.onEach {
            when (it) {
                is Resource.Loading -> _charactersUiState.value =
                    CharactersListUiState(isLoading = true)
                is Resource.Error -> CharactersListUiState(error = handleFailure(it.error))

                is Resource.Success -> {
                    when (it.data?.code) {
                        200 -> {
                            _charactersUiState.value =
                                CharactersListUiState(charactersListDto = it.data)
                        }
                        else -> {
                            CharactersListUiState(
                                error = handleFailure(
                                    Failure.ServerError(
                                        it.data?.code ?: 0, errorMessage = "Server error"
                                    )
                                )
                            )
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

}

data class CharactersListUiState(
    val charactersListDto: CharactersListDto? = null,
    val error: String = "",
    val isLoading: Boolean = false
)