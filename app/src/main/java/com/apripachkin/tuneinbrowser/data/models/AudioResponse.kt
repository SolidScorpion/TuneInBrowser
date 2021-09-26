package com.apripachkin.tuneinbrowser.data.models
data class AudioResponse(
    val head: Head,
    val body: List<AudioElement>
)
data class AudioElement(
    val element: String,
    val url: String,
    val reliability: Int,
    val bitrate: Int,
    val media_type: String,
    val position: Int,
    val player_width: Int,
    val player_height: Int,
    val is_hls_advanced: String,
    val live_seek_stream: String,
    val guide_id: String,
    val is_direct: Boolean,
)
