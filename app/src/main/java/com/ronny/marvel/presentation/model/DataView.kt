package com.ronny.marvel.presentation.model
import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class DataView(
    val offset: Int? = -1,
    val limit: Int? = -1,
    val total: Int?= -1,
    val count: Int?= -1,
    val characterItem: List<CharacterItemView>? = listOf()
): Serializable