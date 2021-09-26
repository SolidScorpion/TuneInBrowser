package com.apripachkin.tuneinbrowser

import com.apripachkin.tuneinbrowser.data.models.AudioOutLine
import com.apripachkin.tuneinbrowser.data.models.HeaderOutLine
import com.apripachkin.tuneinbrowser.data.models.LinkOutLine

object TestObjects {
    val sampleAudioOutLine = AudioOutLine(
        "audio",
        "KNKX (Seattle-Tacoma, US)",
        "http://opml.radiotime.com/Tune.ashx?id=s44526",
        128,
        96,
        "Dinah Washington - This Can't Be Love",
        "mp3",
        "Dinah Washington - This Can't Be Love",
        null,
        "station",
        "http://cdn-profiles.tunein.com/s44526/images/logoq.png?t=155084"
    )
    val sampleLinkOutline =
        LinkOutLine("link", "World Music", "http://opml.radiotime.com/Browse.ashx?id=g22")
    val sampleHeaderOutLine = HeaderOutLine(
        "Stations", "stations", listOf(
            sampleAudioOutLine,
            sampleLinkOutline.copy(
                text = "More Stations",
                URL = "http://opml.radiotime.com/Browse.ashx?offset=26&id=c57944&filter=s",
                key = "nextStations"
            )
        )
    )
}