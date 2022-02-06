package com.williamtan.mangadexlibrary.domain.repository

import com.williamtan.mangadexlibrary.data.enums.ApiResponse
import com.williamtan.mangadexlibrary.domain.model.CoverArt
import com.williamtan.mangadexlibrary.domain.model.Manga
import kotlinx.coroutines.flow.Flow

interface MangaRepository {
    suspend fun getMangaList(limit: Int, offset: Int, order: Map<String, String>): Flow<ApiResponse<List<Manga>>>
    suspend fun getCoverArt(coverArtId: String): Flow<ApiResponse<CoverArt>>
}