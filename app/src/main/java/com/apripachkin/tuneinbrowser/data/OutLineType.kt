package com.apripachkin.tuneinbrowser.data

import com.apripachkin.tuneinbrowser.R

sealed class OutLineType {
    abstract val itemId: Int
}
data class TextOutLine(val text: String, val element: String, val type: String) : OutLineType() {
    override val itemId: Int
        get() = R.layout.text_item_layout
}

data class LinkOutLine(
    val type: String,
    val text: String,
    val URL: String,
    val key: String? = null
) : OutLineType() {
    override val itemId: Int
        get() = R.layout.link_item_layout
}

data class AudioOutLine(
    val type: String,
    val text: String,
    val URL: String,
    val bitrate: Int,
    val reliability: Int,
    val subtext: String,
    val formats: String,
    val playing: String,
    val playing_image: String?,
    val item: String,
    val image: String
) : OutLineType() {
    override val itemId: Int
        get() = R.layout.audio_item_layout
}

data class HeaderOutLine(
    val text: String,
    val key: String? = null,
    val children: List<OutLineType>
) : OutLineType() {
    override val itemId: Int
        get() = R.layout.header_item_layout
}

