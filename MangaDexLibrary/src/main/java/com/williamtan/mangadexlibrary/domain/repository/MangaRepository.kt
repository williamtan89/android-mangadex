package com.williamtan.mangadexlibrary.domain.repository

import com.williamtan.mangadexlibrary.data.enums.ApiResponse
import com.williamtan.mangadexlibrary.domain.model.Manga
import kotlinx.coroutines.flow.Flow

interface MangaRepository {
    suspend fun getMangaList(): Flow<ApiResponse<List<Manga>>>
}