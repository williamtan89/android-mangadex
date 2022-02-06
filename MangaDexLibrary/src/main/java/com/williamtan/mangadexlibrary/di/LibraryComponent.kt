package com.williamtan.mangadexlibrary.di

import com.williamtan.mangadexlibrary.di.data.api.MangaApiModule
import com.williamtan.mangadexlibrary.di.data.mapper.CallMapperModule
import com.williamtan.mangadexlibrary.di.data.mapper.ResponseMapperModule
import com.williamtan.mangadexlibrary.di.data.repository.MangaRepositoryModule
import com.williamtan.mangadexlibrary.di.domain.interactors.UseCaseModule
import com.williamtan.mangadexlibrary.domain.interactors.GetMangaCoverArtUseCase
import com.williamtan.mangadexlibrary.domain.interactors.GetLatestMangaListUseCase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MangaApiModule::class,
        CallMapperModule::class,
        ResponseMapperModule::class,
        UseCaseModule::class,
        MangaRepositoryModule::class
    ]
)
interface LibraryComponent {
    fun getMangaListUseCase(): GetLatestMangaListUseCase
    fun getMangaCoverArtUseCase(): GetMangaCoverArtUseCase
}