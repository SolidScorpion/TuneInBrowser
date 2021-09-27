package com.apripachkin.tuneinbrowser.domain.repo

import com.apripachkin.tuneinbrowser.data.models.AudioResponse
import com.apripachkin.tuneinbrowser.data.models.TuneInResponse

interface RemoteRepository {
    suspend fun loadBaseData(): TuneInResponse
    suspend fun loadDataFromUrl(url: String): TuneInResponse
    suspend fun loadAudioUrl(url: String): AudioResponse
}
