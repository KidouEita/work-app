package com.example.workapplication.api

sealed class FetchResult<T> {
    data class Success<T>(val value: T) : FetchResult<T>()
    data class Error<T>(val exception: Throwable) : FetchResult<T>()
}

sealed class FetchResultWithNoResponse {
    object Success : FetchResultWithNoResponse()
    data class Error(val exception: Throwable) : FetchResultWithNoResponse()
}