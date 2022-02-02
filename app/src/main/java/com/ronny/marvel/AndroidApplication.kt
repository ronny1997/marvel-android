package com.ronny.marvel

import android.app.Application
import com.ronny.marvel.core.di.AppModule
import com.ronny.marvel.core.di.ApplicationComponent
import com.ronny.marvel.core.di.DaggerApplicationComponent


class AndroidApplication : Application() {
    private fun injectMembers() = appComponent.inject(this)


    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }
}