package com.ronny.marvel.core.platform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.ronny.marvel.core.common.NavigationCommand
import com.ronny.marvel.core.util.Event

abstract class BaseViewModel : ViewModel() {

    private val navigation = MutableLiveData<Event<NavigationCommand>>()

    fun getNavigation() = navigation as LiveData<Event<NavigationCommand>>

    private val snackBarError = MutableLiveData<Event<String>>()

    fun getSnackbarError() = snackBarError as LiveData<Event<String>>

    fun navigate(directions: NavDirections) {
        navigation.value = Event(NavigationCommand.To(directions))
    }

    fun navigateBack() {
        navigation.value = Event(NavigationCommand.Back)
    }
}