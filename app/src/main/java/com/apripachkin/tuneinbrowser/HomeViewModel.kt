package com.apripachkin.tuneinbrowser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apripachkin.tuneinbrowser.data.TuneInBrowserService
import com.apripachkin.tuneinbrowser.di.modules.Dispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val service: TuneInBrowserService,
    @Dispatcher.IO private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    fun request() {
        viewModelScope.launch(dispatcher) {
            val basePage = service.basePage()
            basePage.body.forEach {
                Timber.d("Received item: $it")
            }
        }
    }
}