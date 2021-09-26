package com.apripachkin.tuneinbrowser.domain

sealed class UiState<out T>

/*
    Since I wanted to implement swipe to refresh and i'm currently using MutableStateFlow
    it doesn't emit value if the current value that it's holding is the same ( equals() returns true)
    and if new value is not emitted swipe  refresh layout continues animation indefinitely
    This is a quick and dirty hack for this simple app, which would of course require something else
    for production application
 */
data class Success<out T>(val value: T) : UiState<T>() {
    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return value?.hashCode() ?: 0
    }
}

object Loading : UiState<Nothing>()
object Fail : UiState<Nothing>()
