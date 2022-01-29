package com.williamtan.mangadexlibrary.data.api

import com.williamtan.mangadexlibrary.data.model.GetMangaResponse
import retrofit2.Response
import retrofit2.http.GET

interface MangaApi {
    /**
     * Retrieve a list of manga
     */
    @GET("/manga")
    fun getMangaList(): Response<GetMangaResponse>
}