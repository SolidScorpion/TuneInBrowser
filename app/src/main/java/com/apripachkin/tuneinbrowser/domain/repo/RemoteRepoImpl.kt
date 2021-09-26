package com.apripachkin.tuneinbrowser.domain.repo

import com.apripachkin.tuneinbrowser.data.models.AudioResponse
import com.apripachkin.tuneinbrowser.data.models.TuneInResponse
import com.apripachkin.tuneinbrowser.data.service.TuneInBrowserService
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(
    private val service: TuneInBrowserService
) : RemoteRepository {
    override suspend fun loadBaseData(): TuneInResponse = service.basePage()
    override suspend fun loadDataFromUrl(url: String): TuneInResponse = service.customUrl(url)
    override suspend fun loadAudioUrl(url: String): AudioResponse = service.audioUrl(url)
}