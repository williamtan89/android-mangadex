package com.williamtan.mangadexlibrary.di.data.api

import com.williamtan.mangadexlibrary.data.api.MangaApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MangaApiModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        })
        .build()

    @Provides
    @Singleton
    fun provideMangaApi(client: OkHttpClient): MangaApi = Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.mangadex.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MangaApi::class.java)
}