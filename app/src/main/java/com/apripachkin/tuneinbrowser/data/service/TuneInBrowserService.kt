package com.apripachkin.tuneinbrowser.data.service

import com.apripachkin.tuneinbrowser.data.models.AudioResponse
import com.apripachkin.tuneinbrowser.data.models.TuneInResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface TuneInBrowserService {
    @GET("/")
    suspend fun basePage(): TuneInResponse

    @GET
    suspend fun customUrl(@Url endpoint: String): TuneInResponse

    @GET
    suspend fun audioUrl(@Url endpoint: String): AudioResponse
}
