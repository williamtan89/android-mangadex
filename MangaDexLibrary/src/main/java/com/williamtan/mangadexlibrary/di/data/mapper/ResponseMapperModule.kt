package com.williamtan.mangadexlibrary.di.data.mapper

import com.williamtan.mangadexlibrary.data.mapper.ApiResponseMapper
import com.williamtan.mangadexlibrary.data.mapper.GetCoverArtResponseMapper
import com.williamtan.mangadexlibrary.data.mapper.GetMangaResponseMapper
import com.williamtan.mangadexlibrary.data.model.GetCoverArtResponse
import com.williamtan.mangadexlibrary.data.model.GetMangaResponse
import com.williamtan.mangadexlibrary.domain.model.CoverArt
import com.williamtan.mangadexlibrary.domain.model.Manga
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ResponseMapperModule {

    @Provides
    @Singleton
    fun provideGetMangaResponseMapper(): ApiResponseMapper<GetMangaResponse, List<Manga>> =
        GetMangaResponseMapper()

    @Provides
    @Singleton
    fun provideGetCoverArtResponseMapper(): ApiResponseMapper<GetCoverArtResponse, CoverArt> =
        GetCoverArtResponseMapper()
}