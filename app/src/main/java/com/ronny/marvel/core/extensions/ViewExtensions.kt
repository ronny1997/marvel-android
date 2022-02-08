package com.ronny.marvel.core.extensions

import android.view.LayoutInflater
import android.view.View

fun View.getLayoutInflater(): LayoutInflater = LayoutInflater.from(this.context)