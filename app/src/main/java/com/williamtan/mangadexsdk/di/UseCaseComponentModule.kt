package com.williamtan.mangadexsdk.di

import android.app.Application
import com.williamtan.mangadexlibrary.di.DaggerLibraryComponent
import com.williamtan.mangadexlibrary.di.LibraryComponent
import com.williamtan.mangadexlibrary.domain.interactors.GetMangaCoverArtUseCase
import com.williamtan.mangadexlibrary.domain.interactors.GetLatestMangaListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Why we have this? Because we don't want hilt in the library module
 */

@Module
@InstallIn(SingletonComponent::class)
class UseCaseComponentModule {
    @Singleton
    @Provides
    fun provideLibraryComponent(application: Application): LibraryComponent {
        return DaggerLibraryComponent.builder().build()
    }

    @Provides
    fun getMangaListUseCase(libraryComponent: LibraryComponent): GetLatestMangaListUseCase =
        libraryComponent.getMangaListUseCase()

    @Provides
    fun getMangaCoverArtUseCase(libraryComponent: LibraryComponent): GetMangaCoverArtUseCase =
        libraryComponent.getMangaCoverArtUseCase()
}