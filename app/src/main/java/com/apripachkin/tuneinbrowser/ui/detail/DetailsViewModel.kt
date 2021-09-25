package com.apripachkin.tuneinbrowser.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apripachkin.tuneinbrowser.data.HeaderOutLine
import com.apripachkin.tuneinbrowser.data.OutLineType
import com.apripachkin.tuneinbrowser.data.TuneInResponse
import com.apripachkin.tuneinbrowser.data.service.TuneInBrowserService
import com.apripachkin.tuneinbrowser.di.modules.Dispatcher
import com.apripachkin.tuneinbrowser.domain.Fail
import com.apripachkin.tuneinbrowser.domain.Loading
import com.apripachkin.tuneinbrowser.domain.Success
import com.apripachkin.tuneinbrowser.domain.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val service: TuneInBrowserService,
    @Dispatcher.IO private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val dataFlow = MutableStateFlow<UiState<TuneInResponse>>(Loading)
    val data: Flow<UiState<TuneInResponse>> = dataFlow
    fun loadData(url: String) {
        Timber.d("Loading $url")
        viewModelScope.launch(dispatcher) {
            try {
                val customUrl = service.customUrl(url)
                val resultList = mutableListOf<OutLineType>()
                customUrl.body.forEach {
                    if (it is HeaderOutLine) {
                        resultList.add(HeaderOutLine(it.text, it.key, emptyList()))
                        resultList.addAll(it.children)
                    } else {
                        resultList.add(it)
                    }
                }
                dataFlow.emit(Success(customUrl.copy(body = resultList)))
            } catch (e: Exception) {
                Timber.e(e)
                dataFlow.emit(Fail)
            }
        }
    }
}