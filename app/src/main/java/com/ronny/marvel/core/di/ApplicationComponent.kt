package com.ronny.marvel.core.di

import com.ronny.marvel.AndroidApplication
import com.ronny.marvel.core.navigation.MainActivity
import com.ronny.marvel.features.characters.charactersdetail.CharacterDetailFragment
import com.ronny.marvel.features.characters.characterslist.CharactersListFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(application: AndroidApplication)
    fun inject(charactersListFragment: CharactersListFragment)
    fun inject(characterDetailFragment: CharacterDetailFragment)
}