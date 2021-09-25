package com.apripachkin.tuneinbrowser.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apripachkin.tuneinbrowser.di.modules.Dispatcher
import com.apripachkin.tuneinbrowser.domain.Fail
import com.apripachkin.tuneinbrowser.domain.Loading
import com.apripachkin.tuneinbrowser.domain.Success
import com.apripachkin.tuneinbrowser.domain.UiState
import com.apripachkin.tuneinbrowser.domain.interactor.RemoteServiceInteractor
import com.apripachkin.tuneinbrowser.domain.models.UiItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val interactor: RemoteServiceInteractor,
    @Dispatcher.IO private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val dataFlow = MutableStateFlow<UiState<Pair<String?, List<UiItem>>>>(Loading)
    val data: Flow<UiState<Pair<String?,List<UiItem>>>> = dataFlow
    fun loadData(url: String) {
        Timber.d("Loading $url")
        viewModelScope.launch(dispatcher) {
            try {
                val data = interactor.loadRemoteUrl(url)
                dataFlow.emit(Success(data))
            } catch (e: Exception) {
                Timber.e(e)
                dataFlow.emit(Fail)
            }
        }
    }
}