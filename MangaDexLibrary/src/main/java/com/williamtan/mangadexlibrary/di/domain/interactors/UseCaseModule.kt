package com.williamtan.mangadexlibrary.di.domain.interactors

import com.williamtan.mangadexlibrary.domain.interactors.GetMangaCoverArtUseCase
import com.williamtan.mangadexlibrary.domain.interactors.GetMangaCoverArtUseCaseImpl
import com.williamtan.mangadexlibrary.domain.interactors.GetLatestMangaListUseCase
import com.williamtan.mangadexlibrary.domain.interactors.GetLatestMangaListUseCaseImpl
import com.williamtan.mangadexlibrary.domain.repository.MangaRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetMangaCoverArtUseCase(
        repository: MangaRepository
    ): GetMangaCoverArtUseCase = GetMangaCoverArtUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideGetMangaListUseCase(
        repository: MangaRepository
    ): GetLatestMangaListUseCase = GetLatestMangaListUseCaseImpl(repository)
}