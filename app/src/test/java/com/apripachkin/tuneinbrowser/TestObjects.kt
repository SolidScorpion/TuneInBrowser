package com.apripachkin.tuneinbrowser

import com.apripachkin.tuneinbrowser.data.models.AudioElement
import com.apripachkin.tuneinbrowser.data.models.AudioOutLine
import com.apripachkin.tuneinbrowser.data.models.AudioResponse
import com.apripachkin.tuneinbrowser.data.models.Head
import com.apripachkin.tuneinbrowser.data.models.HeaderOutLine
import com.apripachkin.tuneinbrowser.data.models.LinkOutLine
import com.apripachkin.tuneinbrowser.data.models.TextOutLine
import com.apripachkin.tuneinbrowser.data.models.TuneInResponse
import com.apripachkin.tuneinbrowser.domain.models.LinkItem

object TestObjects {
    val sampleTextOutLine = TextOutLine("Sample text", "outline","text")
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

    private val sampleHead = Head("Music", 200)
    val sampleTuneInResponse = TuneInResponse(
        sampleHead,
        listOf(sampleLinkOutline)
    )

    val sampleLinkItem = LinkItem(sampleLinkOutline.text, sampleLinkOutline.URL)

    val sampleAudioResponse = AudioResponse(
        sampleHead,
        listOf(
            AudioElement(
                "audio",
                "someUrl",
                100,
                100,
                "mp3",
                0,
                640,
            640,
                "true",
                "someUrl",
                "1234",
                true
            )
        )
    )
}