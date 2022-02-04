package com.williamtan.mangadexlibrary.data.mapper

import com.williamtan.mangadexlibrary.data.enums.ApiResponse
import com.williamtan.mangadexlibrary.data.model.GetCoverArtResponse
import com.williamtan.mangadexlibrary.domain.model.CoverArt

class GetCoverArtResponseMapper : ApiResponseMapper<GetCoverArtResponse, CoverArt> {
    override fun map(response: GetCoverArtResponse): ApiResponse.Success<CoverArt> {
        return ApiResponse.Success(
            CoverArt(
                fileName = response.data.attributes.fileName
            )
        )
    }
}