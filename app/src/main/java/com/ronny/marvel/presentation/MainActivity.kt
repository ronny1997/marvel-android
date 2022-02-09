package com.ronny.marvel.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ronny.marvel.R
import com.ronny.marvel.AndroidApplication
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__nav_host)
    }
}