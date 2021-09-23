package com.apripachkin.tuneinbrowser.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apripachkin.tuneinbrowser.data.Outline
import com.apripachkin.tuneinbrowser.data.service.TuneInBrowserService
import com.apripachkin.tuneinbrowser.di.modules.Dispatcher
import com.apripachkin.tuneinbrowser.domain.UiState
import com.apripachkin.tuneinbrowser.domain.Fail
import com.apripachkin.tuneinbrowser.domain.Loading
import com.apripachkin.tuneinbrowser.domain.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val service: TuneInBrowserService,
    @Dispatcher.IO private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    val currentData = MutableStateFlow<UiState<List<Outline>>>(Loading)

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch(dispatcher) {
            currentData.emit(Loading)
            try {
                val basePage = service.basePage()
                if (basePage.head.status == 200) {
                    currentData.emit(Success(basePage.body))
                }
            } catch (e: Throwable) {
                currentData.emit(Fail)
            }
        }
    }
}