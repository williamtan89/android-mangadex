package com.williamtan.mangadexlibrary.data.mapper

import com.williamtan.mangadexlibrary.data.enum.ApiResponse
import com.williamtan.mangadexlibrary.data.model.GetMangaResponse
import com.williamtan.mangadexlibrary.domain.model.Manga

internal class GetMangaResponseMapper : ApiResponseMapper<GetMangaResponse, List<Manga>> {
    override fun map(response: GetMangaResponse): ApiResponse.Success<List<Manga>> {
        return ApiResponse.Success(
            response.data.map {
                Manga(
                    id = it.id,
                    title = it.title.values.first(),
                    titleLocale = it.title.keys.first(),
                    altTitle = it.altTitle.map {
                        it.values.first() to it.keys.first()
                    },
                    description = it.description.values.first(),
                    descriptionLocale = it.description.keys.first()
                )
            }
        )
    }
}