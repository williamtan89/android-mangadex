package com.williamtan.mangadexlibrary.data.model

import com.google.gson.annotations.SerializedName
import com.williamtan.mangadexlibrary.data.enums.MangaDexApiResult

data class GetCoverArtResponse(
    @SerializedName("result") val result: MangaDexApiResult,
    @SerializedName("data") val data: CoverArtResponse
)

data class CoverArtResponse(
    @SerializedName("attributes") val attributes: CoverArtAttributesResponse
)

data class CoverArtAttributesResponse(
    @SerializedName("fileName") val fileName: String
)