package com.williamtan.mangadexlibrary.di.domain.interactors

import com.williamtan.mangadexlibrary.domain.interactors.GetMangaListUseCase
import com.williamtan.mangadexlibrary.domain.interactors.GetMangaListUseCaseImpl
import com.williamtan.mangadexlibrary.domain.repository.MangaRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GetMangaListUseCaseModule {
    @Provides
    @Singleton
    fun provideGetMangaListUseCase(
        repository: MangaRepository
    ): GetMangaListUseCase {

        return GetMangaListUseCaseImpl(repository)
    }
}