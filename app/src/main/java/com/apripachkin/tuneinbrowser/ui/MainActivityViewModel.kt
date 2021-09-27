package com.apripachkin.tuneinbrowser.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    private val _titleFlow = MutableStateFlow("")
    val titleFlow: SharedFlow<String> = _titleFlow

    fun updateTitle(title: String) {
        viewModelScope.launch {
            _titleFlow.emit(title)
        }
    }
}
