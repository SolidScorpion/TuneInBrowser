package com.apripachkin.tuneinbrowser.domain.repo

import com.apripachkin.tuneinbrowser.data.models.AudioResponse
import com.apripachkin.tuneinbrowser.data.service.TuneInBrowserService
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(
    private val service: TuneInBrowserService
) : RemoteRepository {
    override suspend fun loadBaseData() = service.basePage()
    override suspend fun loadDataFromUrl(url: String) = service.customUrl(url)
    override suspend fun loadAudioUrl(url: String): AudioResponse = service.audioUrl(url)
}