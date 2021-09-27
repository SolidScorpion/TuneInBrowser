package com.apripachkin.tuneinbrowser.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apripachkin.tuneinbrowser.di.modules.Dispatcher
import com.apripachkin.tuneinbrowser.domain.Fail
import com.apripachkin.tuneinbrowser.domain.Loading
import com.apripachkin.tuneinbrowser.domain.Success
import com.apripachkin.tuneinbrowser.domain.UiState
import com.apripachkin.tuneinbrowser.domain.interactor.RemoteServiceInteractor
import com.apripachkin.tuneinbrowser.domain.models.LinkItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val interactor: RemoteServiceInteractor,
    @Dispatcher.IO private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val currentData = MutableStateFlow<UiState<List<LinkItem>>>(Loading)
    val data: StateFlow<UiState<List<LinkItem>>> = currentData
    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(dispatcher) {
            currentData.emit(Loading)
            try {
                val basePage = interactor.loadBasePage()
                currentData.emit(Success(basePage.filterIsInstance<LinkItem>()))
            } catch (e: Throwable) {
                currentData.emit(Fail)
            }
        }
    }
}
