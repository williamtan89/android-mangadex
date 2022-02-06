package com.williamtan.mangadexlibrary.domain.interactors

import com.williamtan.mangadexlibrary.data.enums.ApiResponse
import com.williamtan.mangadexlibrary.domain.model.Manga
import com.williamtan.mangadexlibrary.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

interface GetLatestMangaListUseCase {
    suspend operator fun invoke(limit: Int, offset: Int): Flow<ApiResponse<List<Manga>>>
}

class GetLatestMangaListUseCaseImpl(private val repository: MangaRepository) :
    GetLatestMangaListUseCase {
    override suspend fun invoke(limit: Int, offset: Int): Flow<ApiResponse<List<Manga>>> =
        repository.getMangaList(limit, offset, mapOf("order[updatedAt]" to "desc"))
}