package com.williamtan.mangadexlibrary.domain.interactors

import com.williamtan.mangadexlibrary.data.enums.ApiResponse
import com.williamtan.mangadexlibrary.domain.model.Manga
import com.williamtan.mangadexlibrary.domain.repository.MangaRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetLatestMangaListUseCaseTest {
    @MockK(relaxed = true)
    private lateinit var repository: MangaRepository

    private lateinit var useCase: GetLatestMangaListUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        useCase = GetLatestMangaListUseCaseImpl(repository)
    }

    @After
    fun cleanUp() = clearAllMocks()

    @Test
    fun `invoke should returns flow from repository`() = runTest {
        val limit = 1
        val offset = 0
        val expected: Flow<ApiResponse<List<Manga>>> = mockk()
        coEvery { repository.getMangaList(limit, offset) } returns expected

        val result = useCase(limit, offset)

        assertEquals(expected, result)
        coVerify { repository.getMangaList(limit, offset) }
    }
}