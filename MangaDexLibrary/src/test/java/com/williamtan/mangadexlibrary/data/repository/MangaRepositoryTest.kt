package com.williamtan.mangadexlibrary.data.repository

import com.williamtan.mangadexlibrary.data.api.MangaApi
import com.williamtan.mangadexlibrary.data.enums.ApiResponse
import com.williamtan.mangadexlibrary.data.mapper.CallMapper
import com.williamtan.mangadexlibrary.data.model.GetCoverArtResponse
import com.williamtan.mangadexlibrary.data.model.GetMangaResponse
import com.williamtan.mangadexlibrary.domain.model.CoverArt
import com.williamtan.mangadexlibrary.domain.model.Manga
import com.williamtan.mangadexlibrary.domain.repository.MangaRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class MangaRepositoryTest {
    @MockK
    private lateinit var api: MangaApi

    @MockK
    private lateinit var getMangaResponseCallMapper: CallMapper<GetMangaResponse, List<Manga>>

    @MockK
    private lateinit var getCoverArtResponseCallMapper: CallMapper<GetCoverArtResponse, CoverArt>

    private lateinit var repository: MangaRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        repository =
            MangaRepositoryImpl(api, getMangaResponseCallMapper, getCoverArtResponseCallMapper)
    }

    @After
    fun cleanUp() = clearAllMocks()

    @Test
    fun `getMangaList result should return as flow`() = runTest {
        val limit = 1
        val offset = 0
        val order = mapOf("order[updatedAt]" to "desc")

        val expected = mockk<ApiResponse.Success<List<Manga>>>()
        val call = mockk<Call<GetMangaResponse>>()
        val flow: Flow<ApiResponse<List<Manga>>> = flow { emit(expected) }

        every { getMangaResponseCallMapper.toFlow(call) } returns flow
        every { api.getMangaList(limit, offset, order) } returns call

        val result = repository.getMangaList(limit, offset).toList()

        assertTrue(result.size == 1)
        assertEquals(expected, result[0])

        return@runTest
    }
}