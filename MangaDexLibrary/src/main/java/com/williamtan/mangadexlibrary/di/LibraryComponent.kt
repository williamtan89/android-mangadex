package com.williamtan.mangadexlibrary.di

import com.williamtan.mangadexlibrary.di.data.api.MangaApiModule
import com.williamtan.mangadexlibrary.di.data.mapper.CallMapperModule
import com.williamtan.mangadexlibrary.di.data.mapper.GetMangaResponseMapperModule
import com.williamtan.mangadexlibrary.di.data.repository.MangaRepositoryModule
import com.williamtan.mangadexlibrary.di.domain.interactors.GetMangaListUseCaseModule
import com.williamtan.mangadexlibrary.domain.interactors.GetMangaListUseCase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MangaApiModule::class,
        CallMapperModule::class,
        GetMangaResponseMapperModule::class,
        MangaRepositoryModule::class,
        GetMangaListUseCaseModule::class
    ]
)
interface LibraryComponent {
    fun getMangaListUseCase(): GetMangaListUseCase
}