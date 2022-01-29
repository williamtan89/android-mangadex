package com.williamtan.mangadexlibrary.data.mapper

import com.williamtan.mangadexlibrary.data.enum.ApiResponse

interface ApiResponseMapper<T, R> {
    fun map(response: T): ApiResponse.Success<R>
}