package com.apripachkin.tuneinbrowser.ui.audio

import com.apripachkin.tuneinbrowser.domain.Fail
import com.apripachkin.tuneinbrowser.domain.Success
import com.apripachkin.tuneinbrowser.domain.interactor.RemoteServiceInteractor
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class AudioViewModelTest {
    private val interactor: RemoteServiceInteractor = mockk(relaxed = true)
    private val dispatcher = TestCoroutineDispatcher()
    private val url = "SomeUrl"
    @Test
    fun testDataIsFetched() = runBlockingTest {
        coEvery { interactor.loadAudioUrl(url) } returns url

        val viewModel = provideViewModel()
        viewModel.fetchAudioLink(url)
        val job = launch {
            viewModel.data.collect {
                assert(it is Success && it.value == url)
            }
        }
        coVerify { interactor.loadAudioUrl(url) }
        job.cancel()
    }

    @Test
    fun testFailIsEmitted() = runBlockingTest {
        coEvery { interactor.loadAudioUrl(url) } throws Error()
        val viewModel = provideViewModel()
        viewModel.fetchAudioLink(url)
        val job = launch {
            viewModel.data.collect {
                assert(it is Fail)
            }
        }
        coVerify { interactor.loadAudioUrl(url) }
        job.cancel()
    }

    private fun provideViewModel() = AudioViewModel(interactor, dispatcher)
}
