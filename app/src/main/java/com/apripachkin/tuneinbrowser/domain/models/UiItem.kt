package com.apripachkin.tuneinbrowser.domain.models

import android.os.Parcelable
import com.apripachkin.tuneinbrowser.R
import kotlinx.parcelize.Parcelize

sealed class UiItem(val itemId: Int) : Parcelable

@Parcelize
data class TextItem(val text: String) : UiItem(R.layout.text_item_layout)

@Parcelize
data class LinkItem(val text: String, val url: String) : UiItem(R.layout.link_item_layout)

@Parcelize
data class AudioItem(
    val text: String,
    val url: String,
    val subText: String,
    val playing: String? = null,
    val playingImage: String? = null,
    val image: String
) : UiItem(R.layout.audio_item_layout)

@Parcelize
data class HeaderItem(
    val text: String,
    val moreItemsLink: String? = null
) : UiItem(R.layout.header_item_layout)
