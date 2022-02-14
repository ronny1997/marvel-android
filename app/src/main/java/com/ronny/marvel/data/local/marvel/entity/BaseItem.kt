package com.ronny.marvel.data.local.marvel.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


open class BaseItem(){
    @SerializedName("resourceURI")
    val resourceURI: String = ""

    @SerializedName("name")
    val name: String = ""
}