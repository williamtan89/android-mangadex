package com.williamtan.mangadexlibrary.data.api

import com.williamtan.mangadexlibrary.data.model.GetCoverArtResponse
import com.williamtan.mangadexlibrary.data.model.GetMangaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MangaApi {
    /**
     * Retrieve a list of manga
     */
    @GET("/manga")
    fun getMangaList(): Call<GetMangaResponse>

    @GET("/cover/{coverArtId}")
    fun getCoverArt(
        @Path("coverArtId") coverArtId: String
    ): Call<GetCoverArtResponse>
}