package com.williamtan.mangadexlibrary.data.mapper

import com.williamtan.mangadexlibrary.data.enum.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call

internal class CallMapper<T, R>(
    private val apiResponseMapper: ApiResponseMapper<T, R>
) {
    fun toFlow(
        call: Call<T>
    ): Flow<ApiResponse<R>> {
        return flow {
            try {
                emit(ApiResponse.Loading<R>())

                val response = call.execute()

                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(apiResponseMapper.map(it))
                    }
                } else {
                    emit(ApiResponse.Error<R>(response.errorBody()?.toString() ?: "unknown error"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                emit(ApiResponse.Loading<R>(false))
            }
        }
    }
}