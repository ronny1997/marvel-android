package com.ronny.marvel.presentation.characterslist

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ronny.marvel.domain.use_case.GetCharactersUseCase
import com.ronny.marvel.features.CharacterRepositoryTest
import com.ronny.marvel.presentation.model.MarvelDataView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

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
        charactersListUiState = CharactersListUiState(charactersListView = MarvelDataView())
        assertEquals(MarvelDataView(), charactersListUiState.charactersListView)
    }

    @Test
    fun `Test return characterListView`() {
        runBlocking {
            getCharactersUseCase.execute(GetCharactersUseCase.Params(0)).collectLatest {
                assertEquals(MarvelDataView(), it.data)
            }
        }
    }
}