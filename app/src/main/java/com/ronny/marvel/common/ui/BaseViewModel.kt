package com.ronny.marvel.common.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.ronny.marvel.R
import com.ronny.marvel.common.util.Failure
import com.ronny.marvel.common.util.Event


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