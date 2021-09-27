package com.apripachkin.tuneinbrowser.ui

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MainActivityViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testTitleIsEmitted() = runBlockingTest {
        val mainActivityViewModel = MainActivityViewModel()
        val newTitle = "NewTitle"
        mainActivityViewModel.updateTitle(newTitle)
        val job = launch {
            mainActivityViewModel.titleFlow.collect {
                assert(it == newTitle)
            }
        }
        job.cancel()

    }
}