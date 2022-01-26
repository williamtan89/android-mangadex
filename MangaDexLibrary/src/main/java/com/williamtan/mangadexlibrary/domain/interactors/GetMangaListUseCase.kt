package com.williamtan.mangadexlibrary.domain.interactors

import com.williamtan.mangadexlibrary.data.enum.ApiResponse
import com.williamtan.mangadexlibrary.domain.model.Manga
import com.williamtan.mangadexlibrary.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

interface GetMangaListUseCase {
    suspend operator fun invoke(): Flow<ApiResponse<List<Manga>>>
}

class GetMangaListUseCaseImpl(private val repository: MangaRepository) : GetMangaListUseCase {
    override suspend fun invoke(): Flow<ApiResponse<List<Manga>>> = repository.getMangaList()
}