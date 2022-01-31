package com.williamtan.mangadexlibrary.di.data.repository

import com.williamtan.mangadexlibrary.data.api.MangaApi
import com.williamtan.mangadexlibrary.data.mapper.CallMapper
import com.williamtan.mangadexlibrary.data.model.GetMangaResponse
import com.williamtan.mangadexlibrary.data.repository.MangaRepositoryImpl
import com.williamtan.mangadexlibrary.domain.model.Manga
import com.williamtan.mangadexlibrary.domain.repository.MangaRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MangaRepositoryModule {
    @Provides
    @Singleton
    fun provideMangaRepository(
        mangaApi: MangaApi,
        callMapper: CallMapper<GetMangaResponse, List<Manga>>
    ): MangaRepository = MangaRepositoryImpl(mangaApi, callMapper)
}