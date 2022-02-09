package com.ronny.marvel.domain.model


import androidx.annotation.Keep
import androidx.appcompat.view.menu.MenuView
import com.google.gson.annotations.SerializedName
import com.ronny.marvel.presentation.model.ItemView
import java.io.Serializable
@Keep
data class Item(
    val resourceURI: String?,
    val name: String?
): Serializable

fun Item.toItemView(): ItemView = ItemView(resourceURI, name)
fun Item.toItem(): Item = Item(resourceURI, name)