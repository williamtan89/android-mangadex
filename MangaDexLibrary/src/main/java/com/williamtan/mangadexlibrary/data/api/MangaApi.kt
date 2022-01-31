package com.williamtan.mangadexlibrary.data.api

import com.williamtan.mangadexlibrary.data.model.GetMangaResponse
import retrofit2.Call
import retrofit2.http.GET

interface MangaApi {
    /**
     * Retrieve a list of manga
     */
    @GET("/manga")
    fun getMangaList(): Call<GetMangaResponse>
}