package com.williamtan.mangadexlibrary.data.mapper

import com.williamtan.mangadexlibrary.data.enums.ApiResponse

interface ApiResponseMapper<T, R> {
    fun map(response: T): ApiResponse.Success<R>
}