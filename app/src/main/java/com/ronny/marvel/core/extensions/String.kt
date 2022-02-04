package com.ronny.marvel.core.extensions

import android.text.Html
import android.widget.TextView
import java.math.BigInteger
import java.security.MessageDigest

fun String.encryptMD5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(this.toByteArray())).toString(16).padStart(32, '0')
}

