package com.apripachkin.tuneinbrowser.domain

sealed class UiState<out T>
data class Success<out T>(val value: T) : UiState<T>() {
    override fun equals(other: Any?): Boolean {
        return false
    }
}
object Loading : UiState<Nothing>()
object Fail : UiState<Nothing>()
