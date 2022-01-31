package com.williamtan.mangadexlibrary.data.enums

sealed class ApiResponse<T>(val data: T?, val message: String?) {
    data class Success<T>(private val _data: T) : ApiResponse<T>(
        data = _data,
        message = null
    )

    data class Error<T>(private val exception: String?) : ApiResponse<T>(
        data = null,
        message = exception
    )

    data class Loading<T>(val isLoading: Boolean = true) : ApiResponse<T>(
        data = null,
        message = null
    )
}