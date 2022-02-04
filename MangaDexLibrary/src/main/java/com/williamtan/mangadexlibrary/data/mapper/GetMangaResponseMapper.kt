package com.williamtan.mangadexlibrary.data.mapper

import com.williamtan.mangadexlibrary.data.enums.ApiResponse
import com.williamtan.mangadexlibrary.data.enums.RelationshipType
import com.williamtan.mangadexlibrary.data.model.GetMangaResponse
import com.williamtan.mangadexlibrary.domain.model.Manga

class GetMangaResponseMapper : ApiResponseMapper<GetMangaResponse, List<Manga>> {
    override fun map(response: GetMangaResponse): ApiResponse.Success<List<Manga>> {
        return ApiResponse.Success(
            response.data.map {
                println(it)

                Manga(
                    id = it.id,
                    title = it.attributes.title.values.first(),
                    titleLocale = it.attributes.title.keys.first(),
                    altTitle = it.attributes.altTitle?.map {
                        it.values.first() to it.keys.first()
                    } ?: emptyList(),
                    description = it.attributes.description.values.firstOrNull(),
                    descriptionLocale = it.attributes.description.keys.firstOrNull(),
                    coverArtId = it.relationships.find { it.type == RelationshipType.CoverArt.type }?.id
                )
            }
        )
    }
}