package com.apripachkin.tuneinbrowser.domain.interactor

import com.apripachkin.tuneinbrowser.TestObjects
import com.apripachkin.tuneinbrowser.domain.mapper.UiItemMapper
import com.apripachkin.tuneinbrowser.domain.models.UiData
import com.apripachkin.tuneinbrowser.domain.repo.RemoteRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class RemoteServiceInteractorImplTest {
    private val remoteRepository = mockk<RemoteRepository>(relaxed = true)
    private val mapper = UiItemMapper()

    @Test
    fun loadBasePage() = runBlocking {
        coEvery { remoteRepository.loadBaseData() } returns TestObjects.sampleTuneInResponse
        val loadBasePage = provideInteractor().loadBasePage()
        coVerify { remoteRepository.loadBaseData() }
        assertEquals(loadBasePage, listOf(TestObjects.sampleLinkItem))
    }

    @Test
    fun loadRemoteUrl() = runBlocking {
        coEvery { remoteRepository.loadDataFromUrl(any()) } returns TestObjects.sampleTuneInResponse
        val url = "some Url"
        val result = provideInteractor().loadRemoteUrl(url)
        coVerify { remoteRepository.loadDataFromUrl(url) }
        assertEquals(UiData("Music", listOf(TestObjects.sampleLinkItem)), result)
    }
    @Test
    fun loadAudioUrl() = runBlocking {
        coEvery { remoteRepository.loadAudioUrl(any()) } returns TestObjects.sampleAudioResponse
        val url = "someUrl"
        val loadAudioUrl = provideInteractor().loadAudioUrl(url)
        coVerify { remoteRepository.loadAudioUrl(url) }
        assertEquals(loadAudioUrl, TestObjects.sampleAudioResponse.body[0].url)
    }

    private fun provideInteractor() = RemoteServiceInteractorImpl(mapper, remoteRepository)
}
