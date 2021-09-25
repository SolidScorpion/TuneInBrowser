package com.apripachkin.tuneinbrowser.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apripachkin.tuneinbrowser.data.LinkOutLine
import com.apripachkin.tuneinbrowser.data.service.TuneInBrowserService
import com.apripachkin.tuneinbrowser.di.modules.Dispatcher
import com.apripachkin.tuneinbrowser.domain.UiState
import com.apripachkin.tuneinbrowser.domain.Fail
import com.apripachkin.tuneinbrowser.domain.Loading
import com.apripachkin.tuneinbrowser.domain.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val service: TuneInBrowserService,
    @Dispatcher.IO private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val currentData = MutableStateFlow<UiState<List<LinkOutLine>>>(Loading)
    val data: StateFlow<UiState<List<LinkOutLine>>> = currentData
    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch(dispatcher) {
            currentData.emit(Loading)
            try {
                val basePage = service.basePage()
                if (basePage.head.status == 200) {
                    currentData.emit(Success(basePage.body.filterIsInstance<LinkOutLine>()))
                }
            } catch (e: Throwable) {
                currentData.emit(Fail)
            }
        }
    }

    fun fetchResponce(url: String) {
        viewModelScope.launch(dispatcher) {
            try {
                val customUrl = service.customUrl(url)
                Timber.d("Got responce $customUrl")
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }
}