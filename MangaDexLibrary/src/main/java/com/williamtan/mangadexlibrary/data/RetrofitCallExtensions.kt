package com.williamtan.mangadexlibrary.data

import com.williamtan.mangadexlibrary.data.enum.ApiResponse
import com.williamtan.mangadexlibrary.data.enum.ApiResponse.Error
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T> Response<T>.toFlow(): Flow<ApiResponse<out T>> {
    return flow {
        try {
            emit(ApiResponse.Loading<T>())

            if (isSuccessful) {
                body()?.let {
                    emit(ApiResponse.Success(it))
                }
            } else {
                emit(Error<T>(errorBody()?.toString() ?: "unknown error"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            emit(ApiResponse.Loading(false))
        }
    }
}