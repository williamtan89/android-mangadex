package com.williamtan.mangadexlibrary.domain.interactors

import com.williamtan.mangadexlibrary.domain.repository.MangaRepository
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.After
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
    fun `invoke should returns flow from repository`() = runBlocking {
        useCase()

        coVerify { repository.getMangaList() }
    }
}