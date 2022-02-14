package com.ronny.marvel.domain.model


import androidx.annotation.Keep
import androidx.appcompat.view.menu.MenuView
import com.google.gson.annotations.SerializedName
import com.ronny.marvel.data.local.marvel.entity.ItemEntity
import com.ronny.marvel.presentation.model.ItemView
import java.io.Serializable
@Keep
data class Item(
    val resourceURI: String?,
    val name: String?
): Serializable

fun Item.toItemView(): ItemView = ItemView(resourceURI, name)
fun Item.toItem(): Item = Item(resourceURI, name)
fun Item.toItemEntity(): ItemEntity = ItemEntity(0,resourceURI?:"", name?:"")