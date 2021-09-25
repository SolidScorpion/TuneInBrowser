package com.apripachkin.tuneinbrowser.domain.interactor

import com.apripachkin.tuneinbrowser.domain.mapper.UiItemMapper
import com.apripachkin.tuneinbrowser.domain.models.UiData
import com.apripachkin.tuneinbrowser.domain.models.UiItem
import com.apripachkin.tuneinbrowser.domain.repo.RemoteRepository
import javax.inject.Inject

interface RemoteServiceInteractor {
    suspend fun loadBasePage(): List<UiItem>
    suspend fun loadRemoteUrl(url: String): UiData
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
        val toUiItems = mapper.toUiItems(body)
        return UiData(head.title, toUiItems)
    }

}