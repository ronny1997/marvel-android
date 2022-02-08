package com.ronny.marvel.features.characters.charactersdetail

import androidx.lifecycle.viewModelScope
import com.ronny.marvel.core.common.Resource
import com.ronny.marvel.core.platform.BaseViewModel
import com.ronny.marvel.features.characters.characterslist.usecase.GetCharacterByIdUseCase
import com.ronny.marvel.features.characters.model.CharacterItemView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterByIdUseCase : GetCharacterByIdUseCase
) : BaseViewModel(){
    private val _characterUiState = MutableStateFlow(CharacterItemViewUiState())
    val characterUiState: StateFlow<CharacterItemViewUiState> = _characterUiState

    fun getCharactersList(id: Int ) {
        getCharacterByIdUseCase(GetCharacterByIdUseCase.Params(id)).onStart {
            _characterUiState.value = CharacterItemViewUiState(isLoading = true)
        }.onEach {
            when (it) {
                is Resource.Loading -> _characterUiState.value =
                    CharacterItemViewUiState(isLoading = true)
                is Resource.Error -> _characterUiState.value =
                    CharacterItemViewUiState(error = handleFailure(it.error))
                is Resource.Success -> {
                    _characterUiState.value =
                        CharacterItemViewUiState(charactersListView = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

}

data class CharacterItemViewUiState(
    val charactersListView: CharacterItemView? = null,
    val error: String = "",
    val isLoading: Boolean = false
)