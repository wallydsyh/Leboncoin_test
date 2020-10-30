package com.leboncoin.test.wallyd.repository

import android.util.Log
import com.leboncoin.test.wallyd.utils.Result
import retrofit2.Response

open class BaseRepository {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {
        val result: Result<T> = safeApiResult(call, errorMessage)
        var data: T? = null
        when (result) {
            is Result.Success -> {
                data = result.data
            }
            is Result.Error -> {
                Log.d("BaseRepository", errorMessage)
            }
        }
        return data
    }


}

private suspend fun <T : Any> safeApiResult(
    call: suspend () -> Response<T>,
    errorMessage: String
): Result<T> {
    val response = call.invoke()
    if (response.isSuccessful) {
        return Result.Success(response.body()!!)
    }
    return Result.Error(errorMessage)
}