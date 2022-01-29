package com.williamtan.mangadexlibrary.domain.interactors

import com.williamtan.mangadexlibrary.data.enum.ApiResponse
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

class GetMangaListUseCaseTest {
    @MockK(relaxed = true)
    private lateinit var repository: MangaRepository

    private lateinit var useCase: GetMangaListUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        useCase = GetMangaListUseCaseImpl(repository)
    }

    @After
    fun cleanUp() = clearAllMocks()

    @Test
    fun `invoke should returns flow from repository`() = runTest {
        val expected: Flow<ApiResponse<List<Manga>>> = mockk()
        coEvery { repository.getMangaList() } returns expected

        val result = useCase()

        assertEquals(expected, result)
        coVerify { repository.getMangaList() }
    }
}