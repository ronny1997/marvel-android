package com.ronny.marvel.presentation.characterslist

import androidx.lifecycle.viewModelScope
import com.ronny.marvel.common.interactor.None
import com.ronny.marvel.common.ui.BaseViewModel
import com.ronny.marvel.common.util.Resource
import com.ronny.marvel.domain.use_case.GetCharacterRemoteUseCase
import com.ronny.marvel.domain.use_case.GetCharactersUseCase
import com.ronny.marvel.presentation.model.CharacterView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharacterRemoteUseCase: GetCharacterRemoteUseCase
) : BaseViewModel() {
    private val _charactersUiState = MutableStateFlow(CharactersListUiState())
    val charactersUiState: StateFlow<CharactersListUiState> get() = _charactersUiState

    val lastVisibility = MutableStateFlow(0)

    init {
        getCharactersList()
        viewModelScope.launch {
            lastVisibility.collect {
                getCharacterRemote(it)
            }
        }
    }

    private fun getCharactersList() {
        getCharactersUseCase(None()).onStart {
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

    suspend fun getCharacterRemote(offset: Int = 0) {
        getCharacterRemoteUseCase(GetCharacterRemoteUseCase.Params(offset)).onStart {
            _charactersUiState.value = CharactersListUiState(isLoading = true)
        }.collect {
            when (it) {
                is Resource.Error -> _charactersUiState.value =
                    CharactersListUiState(error = handleFailure(it.error))
                is Resource.Success -> {
                    _charactersUiState.value = CharactersListUiState()
                }
            }
        }
    }

    fun goToCharacterDetail(character: CharacterView) {
        navigate(CharactersListFragmentDirections.goToCharacterDetailFragment(character))
    }

}

data class CharactersListUiState(
    val charactersListView: List<CharacterView>? = null,
    val error: String = "",
    val isLoading: Boolean = false
)