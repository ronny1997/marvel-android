package com.ronny.marvel.presentation.charactersdetail

import androidx.lifecycle.viewModelScope
import com.ronny.marvel.common.ui.BaseViewModel
import com.ronny.marvel.common.util.Resource
import com.ronny.marvel.domain.use_case.GetCharacterByIdUseCase
import com.ronny.marvel.presentation.model.CharacterItemView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : BaseViewModel() {
    private val _characterUiState = MutableStateFlow(CharacterItemViewUiState())
    val characterUiState: StateFlow<CharacterItemViewUiState> get() = _characterUiState


     fun getCharactersList(id: Int) {
        viewModelScope.launch {
            getCharacterByIdUseCase(GetCharacterByIdUseCase.Params(id)).collect {
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
            }
        }
    }
}

data class CharacterItemViewUiState(
    val charactersListView: CharacterItemView? = null,
    val error: String = "",
    val isLoading: Boolean = false
)