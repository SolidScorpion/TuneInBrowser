package com.apripachkin.tuneinbrowser.domain.repo

import com.apripachkin.tuneinbrowser.data.service.TuneInBrowserService
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class RemoteRepoImplTest {
    private val serviceMock = mockk<TuneInBrowserService>(relaxed = true)
    private val url = "someUrl"

    @Test
    fun testLoadBaseData() = runBlocking {
        provideRepo().loadBaseData()
        coVerify { serviceMock.basePage() }
    }

    @Test
    fun loadDataFromUrl() = runBlocking {
        provideRepo().loadDataFromUrl(url)
        coVerify { serviceMock.customUrl(url) }
    }

    @Test
    fun loadAudioUrl() = runBlocking {
        provideRepo().loadAudioUrl(url)
        coVerify { serviceMock.audioUrl(url) }
    }

    private fun provideRepo() = RemoteRepoImpl(serviceMock)
}