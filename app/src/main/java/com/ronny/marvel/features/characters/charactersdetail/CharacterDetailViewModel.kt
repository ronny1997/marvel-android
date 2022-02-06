package com.ronny.marvel.features.characters.charactersdetail

import androidx.lifecycle.viewModelScope
import com.ronny.marvel.core.common.Resource
import com.ronny.marvel.core.exception.Failure
import com.ronny.marvel.core.platform.BaseViewModel
import com.ronny.marvel.features.characters.charactersdetail.usecase.GetCharacterUseCase
import com.ronny.marvel.features.characters.characterslist.CharactersListUiState
import com.ronny.marvel.features.characters.model.CharacterItem
import com.ronny.marvel.features.characters.model.CharactersListDto
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase
) : BaseViewModel() {

    private val _characterDetailUiState = MutableStateFlow(CharacterDetailUiState())
    val characterDetailUiState: StateFlow<CharacterDetailUiState> = _characterDetailUiState


    fun getCharacterDetail(id: Int) {
        getCharacterUseCase(GetCharacterUseCase.Params(id)).onStart {
            _characterDetailUiState.value = CharacterDetailUiState(isLoading = true)
        }.onEach {
            when (it) {
                is Resource.Loading -> _characterDetailUiState.value =
                    CharacterDetailUiState(isLoading = true)
                is Resource.Error -> CharacterDetailUiState(error = handleFailure(it.error))

                is Resource.Success -> {
                    when (it.data?.code) {
                        200 -> {
                            it.data.data?.characterItem?.filter { it.id == id }?.forEach { item ->
                                _characterDetailUiState.value =
                                    CharacterDetailUiState(characterItem = item)
                            }

                        }
                        else -> {
                            CharacterDetailUiState(
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

data class CharacterDetailUiState(
    val characterItem: CharacterItem? = null,
    val error: String = "",
    val isLoading: Boolean = false
)