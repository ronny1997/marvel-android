package com.ronny.marvel.common.util

import retrofit2.Call

fun <T, R> request(
    networkHandler: NetworkHandler,
    call: Call<T>,
    transform: (T) -> R,
    default: T,
    ): Resource<Failure, R> {
    var exception: Throwable? = null
    when (networkHandler.isNetworkAvailable()) {
        true -> {
            exception = runCatching {
                call.execute()
            }.onSuccess { response ->
                return when (response.isSuccessful) {
                    true -> Resource.Success(transform((response.body() ?: default)))
                    false -> Resource.Error(
                        Failure.ServerError(
                            response.code(),
                            response.message()
                        )
                    )
                }
            }.exceptionOrNull()
        }
        false -> {
            return Resource.Error(Failure.NetworkConnection(errorMessage = "NO INTERNET \nPlease check your network connection."))
        }
    }


    return Resource.Error(
        Failure.ServerError(
            exception?.stackTrace.hashCode(),
            exception?.localizedMessage ?: "Error"
        )
    )
}
