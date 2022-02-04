package com.williamtan.mangadexlibrary.data.api

import com.williamtan.mangadexlibrary.data.model.GetCoverArtResponse
import com.williamtan.mangadexlibrary.data.model.GetMangaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MangaApi {
    /**
     * Retrieve a list of manga
     */
    @GET("/manga")
    fun getMangaList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @QueryMap(encoded = true) order: Map<String, String>
    ): Call<GetMangaResponse>

    @GET("/cover/{coverArtId}")
    fun getCoverArt(
        @Path("coverArtId") coverArtId: String
    ): Call<GetCoverArtResponse>
}