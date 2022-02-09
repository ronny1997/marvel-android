package com.ronny.marvel.domain.model


import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class Data(
    val offset: Int? = -1,
    val limit: Int? = -1,
    val total: Int?= -1,
    val count: Int?= -1,
    val characterItem: List<CharacterItem>? = listOf()
): Serializable