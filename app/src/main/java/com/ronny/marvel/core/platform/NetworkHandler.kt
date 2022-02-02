package com.ronny.marvel.core.platform

import android.content.Context
import android.net.NetworkCapabilities
import com.ronny.marvel.core.extensions.connectivityManager
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Injectable class which returns information about the network connection state.
 */
@Singleton
class NetworkHandler @Inject constructor( private val context: Context) {
    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.connectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }
}