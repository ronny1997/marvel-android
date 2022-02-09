package com.ronny.marvel.common.util

import com.ronny.marvel.R
import com.ronny.marvel.common.util.Resource
import com.ronny.marvel.data.exception.Failure
import retrofit2.Call
import java.io.IOException
import java.lang.Exception

fun <T, R> request(
    call: Call<T>,
    transform: (T) -> R,
    default: T
): Resource<Failure, R> {
    val exception = runCatching {
        call.execute()
    }.onSuccess { response ->
        return when (response.isSuccessful) {
            true -> Resource.Success(transform((response.body() ?: default)))
            false -> Resource.Error(Failure.ServerError(response.code(), response.message()))
        }
    }.exceptionOrNull()

    return Resource.Error(
        Failure.CustomError(
            exception?.stackTrace.hashCode(),
            exception?.localizedMessage ?: "Error"
        )
    )
}
