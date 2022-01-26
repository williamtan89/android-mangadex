package com.williamtan.mangadexlibrary.data.model

import com.williamtan.mangadexlibrary.data.enum.MangaDexApiResult

data class GetMangaResponse(
    val result: MangaDexApiResult,
    val data: List<MangaResponse>
)

data class MangaResponse(
    val id: String,

    // attributes
    val title: Map<String, String>,
    val altTitle: List<Map<String, String>>,
    val description: Map<String, String>
)