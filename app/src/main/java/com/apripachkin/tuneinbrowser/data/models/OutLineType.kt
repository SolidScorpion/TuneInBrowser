package com.apripachkin.tuneinbrowser.data.models

sealed class OutLineType

data class TextOutLine(val text: String, val element: String, val type: String) : OutLineType()

data class LinkOutLine(
    val type: String,
    val text: String,
    val URL: String,
    val key: String? = null
) : OutLineType()

data class AudioOutLine(
    val type: String,
    val text: String,
    val URL: String,
    val bitrate: Int?,
    val reliability: Int?,
    val subtext: String,
    val formats: String?,
    val playing: String?,
    val playing_image: String?,
    val item: String?,
    val image: String
) : OutLineType()

data class HeaderOutLine(
    val text: String,
    val key: String? = null,
    val children: List<OutLineType>
) : OutLineType()

