package com.williamtan.mangadexlibrary.data.model

import com.google.gson.annotations.SerializedName
import com.williamtan.mangadexlibrary.data.enums.MangaDexApiResult

data class GetMangaResponse(
    @SerializedName("result") val result: MangaDexApiResult,
    @SerializedName("data") val data: List<MangaResponse>
) : GenericResponse()

data class MangaResponse(
    @SerializedName("id") val id: String,
    @SerializedName("attributes") val attributes: MangaAttributesResponse
) : GenericResponse()

data class MangaAttributesResponse(
    @SerializedName("title") val title: Map<String, String>,
    @SerializedName("altTitle") val altTitle: List<Map<String, String>>?,
    @SerializedName("description") val description: Map<String, String>
)