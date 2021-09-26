package com.apripachkin.tuneinbrowser.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apripachkin.tuneinbrowser.di.modules.Dispatcher
import com.apripachkin.tuneinbrowser.domain.Fail
import com.apripachkin.tuneinbrowser.domain.Loading
import com.apripachkin.tuneinbrowser.domain.Success
import com.apripachkin.tuneinbrowser.domain.UiState
import com.apripachkin.tuneinbrowser.domain.interactor.RemoteServiceInteractor
import com.apripachkin.tuneinbrowser.domain.models.UiData
import com.apripachkin.tuneinbrowser.domain.models.UiItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val interactor: RemoteServiceInteractor,
    @Dispatcher.IO private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val dataFlow = MutableStateFlow<UiState<UiData>>(Loading)
    val data: Flow<UiState<UiData>> = dataFlow
    fun loadData(url: String) {
        Timber.d("Loading $url")
        viewModelScope.launch(dispatcher) {
            try {
                val data = interactor.loadRemoteUrl(url)
                dataFlow.emit(Success(data))
            } catch (e: Throwable) {
                Timber.e(e)
                dataFlow.emit(Fail)
            }
        }
    }
}