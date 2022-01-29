package com.williamtan.mangadexlibrary.data.repository

import com.williamtan.mangadexlibrary.data.api.MangaApi
import com.williamtan.mangadexlibrary.data.enum.ApiResponse
import com.williamtan.mangadexlibrary.data.mapper.CallMapper
import com.williamtan.mangadexlibrary.data.model.GetMangaResponse
import com.williamtan.mangadexlibrary.domain.model.Manga
import com.williamtan.mangadexlibrary.domain.repository.MangaRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

internal class MangaRepositoryImpl(
    private val mangaApi: MangaApi,
    private val callMapper: CallMapper<GetMangaResponse, List<Manga>>,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
) : MangaRepository {
    override suspend fun getMangaList(): Flow<ApiResponse<List<Manga>>> =
        callMapper.toFlow(mangaApi.getMangaList()).flowOn(dispatcherIO)
}