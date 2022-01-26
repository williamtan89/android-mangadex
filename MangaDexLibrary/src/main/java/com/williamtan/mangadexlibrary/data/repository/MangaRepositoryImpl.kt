package com.williamtan.mangadexlibrary.data.repository

import com.williamtan.mangadexlibrary.data.api.MangaApi
import com.williamtan.mangadexlibrary.data.enum.ApiResponse
import com.williamtan.mangadexlibrary.data.toFlow
import com.williamtan.mangadexlibrary.domain.model.Manga
import com.williamtan.mangadexlibrary.domain.repository.MangaRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


internal class MangaRepositoryImpl(
    private val mangaApi: MangaApi,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
) : MangaRepository {
    override suspend fun getMangaList(): Flow<ApiResponse<List<Manga>>> =
        mangaApi.getMangaList().toFlow().map {
            when (it) {
                is ApiResponse.Success -> ApiResponse.Success(
                    it.data?.data?.map {
                        Manga(
                            id = it.id,
                            title = it.title.values.first(),
                            titleLocale = it.title.keys.first(),
                            altTitle = it.altTitle.map {
                                it.values.first() to it.keys.first()
                            },
                            description = it.description.values.first(),
                            descriptionLocale = it.description.keys.first()
                        )
                    } ?: emptyList()
                )
                is ApiResponse.Error -> ApiResponse.Error(it.message)
                is ApiResponse.Loading -> ApiResponse.Loading(it.isLoading)
            }
        }.flowOn(dispatcherIO)

}