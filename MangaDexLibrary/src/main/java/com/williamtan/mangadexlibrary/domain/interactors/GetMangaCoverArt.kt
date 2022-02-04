package com.williamtan.mangadexlibrary.domain.interactors

import com.williamtan.mangadexlibrary.data.enums.ApiResponse
import com.williamtan.mangadexlibrary.domain.model.CoverArt
import com.williamtan.mangadexlibrary.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

interface GetMangaCoverArtUseCase {
    suspend operator fun invoke(coverArtId: String): Flow<ApiResponse<CoverArt>>
}

class GetMangaCoverArtUseCaseImpl(private val repository: MangaRepository) :
    GetMangaCoverArtUseCase {
    override suspend fun invoke(coverArtId: String): Flow<ApiResponse<CoverArt>> =
        repository.getCoverArt(coverArtId)
}