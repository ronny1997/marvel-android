package com.ronny.marvel.core.extensions

import android.app.Activity

fun Activity?.getIfActive() =
    if (this.canUse()) {
        this
    } else {
        null
    }

fun Activity?.canUse() = this?.isFinishing == false