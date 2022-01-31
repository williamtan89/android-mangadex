package com.williamtan.mangadexlibrary.di.data.mapper

import com.williamtan.mangadexlibrary.data.mapper.ApiResponseMapper
import com.williamtan.mangadexlibrary.data.mapper.CallMapper
import com.williamtan.mangadexlibrary.data.model.GetMangaResponse
import com.williamtan.mangadexlibrary.domain.model.Manga
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CallMapperModule {
    @Provides
    @Singleton
    fun provideGetMangaResponseCallMapper(
        apiResponseMapper: ApiResponseMapper<GetMangaResponse, List<Manga>>
    ) = CallMapper(apiResponseMapper)
}