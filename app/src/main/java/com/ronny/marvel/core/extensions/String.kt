package com.ronny.marvel.core.extensions

import android.text.Html
import android.widget.TextView

fun TextView.formatHtml(texHtml: String) {

    this.text = Html.fromHtml(texHtml, Html.FROM_HTML_SEPARATOR_LINE_BREAK_BLOCKQUOTE)

}