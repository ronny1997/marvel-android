package com.ronny.marvel.features.characters.charactersdetail

import com.ronny.marvel.core.platform.BaseViewModel
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(
) : BaseViewModel() {

    fun back() {
        navigateBack()
    }

}

