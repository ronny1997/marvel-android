package com.ronny.marvel.features.characters.characterslist

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ronny.marvel.core.common.Resource
import com.ronny.marvel.features.CharacterRepositoryTest
import com.ronny.marvel.features.characters.characterslist.usecase.GetCharactersUseCase
import com.ronny.marvel.features.characters.model.CharactersListView
import com.ronny.marvel.features.characters.model.toCharactersListView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runner.notification.Failure

@RunWith(AndroidJUnit4::class)
class CharactersListViewModelTest {
    private lateinit var viewModel: CharactersListViewModel
    private lateinit var getCharactersUseCase: GetCharactersUseCase
    private var charactersListUiState = CharactersListUiState()

    @Before
    fun setUp() {
        getCharactersUseCase = GetCharactersUseCase(CharacterRepositoryTest())
        viewModel = CharactersListViewModel(getCharactersUseCase)
    }

    @Test
    fun `State is loading`() {
        charactersListUiState = CharactersListUiState(isLoading = true)
        assertEquals(true, charactersListUiState.isLoading)
    }

    @Test
    fun `State is error`() {
        charactersListUiState = CharactersListUiState(error = "Error")
        assertEquals("Error", charactersListUiState.error)
    }

    @Test
    fun `State is success`() {
        charactersListUiState = CharactersListUiState(charactersListView = CharactersListView())
        assertEquals(CharactersListView(), charactersListUiState.charactersListView)
    }

    @Test
    fun `Test return characterListView`() {
        runBlocking {
            getCharactersUseCase.execute(GetCharactersUseCase.Params(0)).collectLatest {
                assertEquals(CharactersListView(), it.data)
            }
        }
    }
}