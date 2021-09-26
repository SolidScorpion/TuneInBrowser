package com.apripachkin.tuneinbrowser.ui.audio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apripachkin.tuneinbrowser.data.models.AudioElement
import com.apripachkin.tuneinbrowser.di.modules.Dispatcher
import com.apripachkin.tuneinbrowser.domain.Fail
import com.apripachkin.tuneinbrowser.domain.Loading
import com.apripachkin.tuneinbrowser.domain.Success
import com.apripachkin.tuneinbrowser.domain.UiState
import com.apripachkin.tuneinbrowser.domain.interactor.RemoteServiceInteractor
import com.apripachkin.tuneinbrowser.domain.models.AudioItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AudioViewModel @Inject constructor(
    private val remoteServiceInteractor: RemoteServiceInteractor,
    @Dispatcher.IO private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private lateinit var audioItem: AudioItem
    private val audioData = MutableStateFlow<UiState<AudioElement>>(Loading)
    val data: StateFlow<UiState<AudioElement>> = audioData

    fun fetchAudioLink(item: AudioItem) {
        audioItem = item
        viewModelScope.launch(dispatcher) {
            try {
                val loadAudioUrl = remoteServiceInteractor.loadAudioUrl(item.url)
                Timber.d("Received audio response: $loadAudioUrl")
                audioData.emit(Success(loadAudioUrl.body[0]))
            } catch (e: Exception) {
                Timber.e(e)
                audioData.emit(Fail)
            }
        }
    }
}