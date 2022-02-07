package com.ronny.marvel.core.platform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.ronny.marvel.R
import com.ronny.marvel.core.common.NavigationCommand
import com.ronny.marvel.core.exception.Failure
import com.ronny.marvel.core.util.Event


abstract class BaseViewModel : ViewModel() {

    private val navigation = MutableLiveData<Event<NavigationCommand>>()

    fun getNavigation() = navigation as LiveData<Event<NavigationCommand>>

    fun navigate(directions: NavDirections) {
        navigation.value = Event(NavigationCommand.To(directions))
    }

    fun navigateBack() {
        navigation.value = Event(NavigationCommand.Back)
    }

     fun handleFailure(failure: Failure?): String = when (failure) {
        is Failure.NetworkConnection -> failure.errorMessage
        is Failure.ServerError -> failure.errorMessage
        is Failure.CustomError -> failure.errorMessage
        else -> R.string.unknown_Error.toString()
    }
}