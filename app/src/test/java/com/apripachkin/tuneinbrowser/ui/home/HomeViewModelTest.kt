package com.apripachkin.tuneinbrowser.ui.home

import com.apripachkin.tuneinbrowser.TestObjects
import com.apripachkin.tuneinbrowser.domain.Fail
import com.apripachkin.tuneinbrowser.domain.Loading
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
class HomeViewModelTest {
    private val interactor: RemoteServiceInteractor = mockk(relaxed = true)
    private val dispatcher = TestCoroutineDispatcher()

    @Test
    fun testDataIsFetched() = runBlockingTest {
        val listOf = listOf(TestObjects.sampleLinkItem)
        coEvery { interactor.loadBasePage() } returns listOf
        val job = launch {
            provideViewModel().data.collect {
                assert(it is Success && it.value == listOf)
            }
        }
        coVerify { interactor.loadBasePage() }
        job.cancel()
    }

    @Test
    fun testFailIsEmitted() = runBlockingTest {
        coEvery { interactor.loadBasePage() } throws Error()
        val job = launch {
            provideViewModel().data.collect {
                assert(it is Fail)
            }
        }
        coVerify { interactor.loadBasePage() }
        job.cancel()
    }

    private fun provideViewModel() = HomeViewModel(interactor, dispatcher)
}