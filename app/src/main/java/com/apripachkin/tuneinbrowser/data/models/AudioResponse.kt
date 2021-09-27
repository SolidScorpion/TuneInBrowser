package com.apripachkin.tuneinbrowser.data.models

import com.squareup.moshi.Json

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
    @Json(name = "player_width")
    val playerWidth: Int,
    @Json(name = "player_height")
    val playerHeight: Int,
    @Json(name = "is_hls_advanced")
    val isHlsAdvanced: String,
    @Json(name = "live_seek_stream")
    val liveSeekStream: String,
    @Json(name = "guide_id")
    val guideId: String,
    @Json(name = "is_direct")
    val isDirect: Boolean,
)
