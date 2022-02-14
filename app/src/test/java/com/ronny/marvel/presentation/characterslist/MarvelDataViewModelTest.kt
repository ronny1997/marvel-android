package com.ronny.marvel.presentation.characterslist

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ronny.marvel.common.interactor.None
import com.ronny.marvel.domain.use_case.GetCharacterRemoteUseCase
import com.ronny.marvel.domain.use_case.GetCharactersUseCase
import com.ronny.marvel.features.CharacterRepositoryTest
import com.ronny.marvel.presentation.model.CharacterView
import com.ronny.marvel.presentation.model.MarvelDataView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MarvelDataViewModelTest {
    private lateinit var viewModel: CharactersListViewModel
    private lateinit var getCharactersUseCase: GetCharactersUseCase
    private lateinit var getCharacterRemoteUseCase: GetCharacterRemoteUseCase
    private var charactersListUiState = CharactersListUiState()

    @Before
    fun setUp() {
        getCharactersUseCase = GetCharactersUseCase(CharacterRepositoryTest())
        getCharacterRemoteUseCase = GetCharacterRemoteUseCase(CharacterRepositoryTest())
        viewModel = CharactersListViewModel(getCharactersUseCase, getCharacterRemoteUseCase)
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
        val listCharacters = listOf<CharacterView>()
        charactersListUiState = CharactersListUiState(charactersListView = listCharacters)
        assertEquals(listOf<CharacterView>(), charactersListUiState.charactersListView)
    }
}