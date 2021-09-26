package com.apripachkin.tuneinbrowser.ui.detail

import com.apripachkin.tuneinbrowser.TestObjects
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
class DetailsViewModelTest {
    private val interactor: RemoteServiceInteractor = mockk(relaxed = true)
    private val dispatcher = TestCoroutineDispatcher()
    private val url = "SomeUrl"
    @Test
    fun testSuccessIsEmitted() = runBlockingTest {
        coEvery { interactor.loadRemoteUrl(url) } returns TestObjects.sampleUiData
        val viewModel = provideViewModel()
        viewModel.loadData(url)
        val job = launch {
            viewModel.data.collect {
                assert(it is Success && it.value == TestObjects.sampleUiData)
            }
        }
        coVerify { interactor.loadRemoteUrl(url) }
        job.cancel()
    }

    @Test
    fun testFailIsEmitted() = runBlockingTest {
        coEvery { interactor.loadRemoteUrl(url) } throws Error()
        val viewModel = provideViewModel()
        viewModel.loadData(url)
        val job = launch {
            viewModel.data.collect {
                assert(it is Fail)
            }
        }
        coVerify { interactor.loadRemoteUrl(url) }
        job.cancel()
    }

    private fun provideViewModel() = DetailsViewModel(interactor, dispatcher)
}