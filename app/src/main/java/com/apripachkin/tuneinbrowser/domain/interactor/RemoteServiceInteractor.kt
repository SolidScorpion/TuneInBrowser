package com.apripachkin.tuneinbrowser.domain.interactor

import com.apripachkin.tuneinbrowser.domain.mapper.UiItemMapper
import com.apripachkin.tuneinbrowser.domain.models.UiData
import com.apripachkin.tuneinbrowser.domain.models.UiItem
import com.apripachkin.tuneinbrowser.domain.repo.RemoteRepository
import javax.inject.Inject

interface RemoteServiceInteractor {
    /**
     * For loading main page categories
     * Data gets mapped into domain UiItem
     */
    suspend fun loadBasePage(): List<UiItem>

    /** Method to load new data based from url returned by api
     * Data gets mapped into wrapped class
     * @param url URL returned from api to use for fetching next data
     * @return wrapped class containing optional title for toolbar and list of new data to show
     */
    suspend fun loadRemoteUrl(url: String): UiData

    /**
     * For loading audio links data
     * Data is mapped as url to be used for streaming
     * @param audio link data to load
     * @return radio station stream URL to be used for streaming
     */
    suspend fun loadAudioUrl(url: String): String
}

class RemoteServiceInteractorImpl @Inject constructor(
    private val mapper: UiItemMapper,
    private val remoteRepository: RemoteRepository
) : RemoteServiceInteractor {
    override suspend fun loadBasePage(): List<UiItem> {
        val loadBaseData = remoteRepository.loadBaseData()
        return mapper.toUiItems(loadBaseData.body)
    }

    override suspend fun loadRemoteUrl(url: String): UiData {
        val (head, body) = remoteRepository.loadDataFromUrl(url)
        val uiItems = mapper.toUiItems(body)
        return UiData(head.title, uiItems)
    }

    override suspend fun loadAudioUrl(url: String): String {
        val loadAudioUrl = remoteRepository.loadAudioUrl(url)
        return loadAudioUrl.body[0].url
    }

}