package com.williamtan.mangadexlibrary.data.repository

import com.williamtan.mangadexlibrary.data.api.MangaApi
import com.williamtan.mangadexlibrary.data.enum.ApiResponse
import com.williamtan.mangadexlibrary.data.model.GetMangaResponse
import com.williamtan.mangadexlibrary.data.model.MangaResponse
import com.williamtan.mangadexlibrary.domain.repository.MangaRepository
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class MangaRepositoryTest {
    @MockK
    private lateinit var api: MangaApi

    private lateinit var repository: MangaRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        repository = MangaRepositoryImpl(api)
    }

    @After
    fun cleanUp() = clearAllMocks()

    @Test
    fun `getMangaList should return ApiResponseSuccess if api response is successful`() = runTest {
        val response = mockk<Response<GetMangaResponse>>()
        val body = mockk<GetMangaResponse>()
        val bodyResponse: List<MangaResponse> = emptyList()

        every { body.data } returns bodyResponse
        every { response.body() } returns body
        every { response.isSuccessful } returns true
        every { api.getMangaList() } returns response

        val result = repository.getMangaList().toList()

        val isLoadingTrue = result[0]
        assertTrue(isLoadingTrue is ApiResponse.Loading && isLoadingTrue.isLoading)

        val expected = result[1]
        assertTrue(expected is ApiResponse.Success && expected.data?.isEmpty() == true)

        val isLoadingFalse = result[2]
        assertTrue(isLoadingFalse is ApiResponse.Loading && !isLoadingFalse.isLoading)
    }
}