package com.williamtan.mangadexlibrary.data.repository

import com.williamtan.mangadexlibrary.data.api.MangaApi
import com.williamtan.mangadexlibrary.data.enums.ApiResponse
import com.williamtan.mangadexlibrary.data.mapper.CallMapper
import com.williamtan.mangadexlibrary.data.model.GetMangaResponse
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
    private lateinit var mapper: CallMapper<GetMangaResponse, List<Manga>>

    private lateinit var repository: MangaRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        repository = MangaRepositoryImpl(api, mapper)
    }

    @After
    fun cleanUp() = clearAllMocks()

    @Test
    fun `getMangaList result should return as flow`() = runTest {
        val expected = mockk<ApiResponse.Success<List<Manga>>>()
        val call = mockk<Call<GetMangaResponse>>()
        val flow: Flow<ApiResponse<List<Manga>>> = flow { emit(expected) }

        every { mapper.toFlow(call) } returns flow
        every { api.getMangaList() } returns call

        val result = repository.getMangaList().toList()

        assertTrue(result.size == 1)
        assertEquals(expected, result[0])

        return@runTest
    }
}