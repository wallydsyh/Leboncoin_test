package com.leboncoin.test.wallyd.utils

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val errorMessage: String) : Result<Nothing>()
}