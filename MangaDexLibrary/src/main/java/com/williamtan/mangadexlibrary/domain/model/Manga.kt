package com.williamtan.mangadexlibrary.domain.model

data class Manga(
    val id: String,

    val title: String,
    val titleLocale: String,
    val altTitle: List<Pair<String, String>>,

    val description: String?,
    val descriptionLocale: String?,

    var coverArtId: String?
)