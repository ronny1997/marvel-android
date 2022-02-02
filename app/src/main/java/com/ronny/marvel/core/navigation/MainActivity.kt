package com.ronny.marvel.core.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ronny.marvel.R
import com.ronny.marvel.AndroidApplication
import com.ronny.marvel.core.di.ApplicationComponent


class MainActivity : AppCompatActivity() {

    private val appComponent: ApplicationComponent by lazy(LazyThreadSafetyMode.NONE) {
        (application as AndroidApplication).appComponent
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__nav_host)
    }



}