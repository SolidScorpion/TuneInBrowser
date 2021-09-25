package com.apripachkin.tuneinbrowser.data.service

import com.apripachkin.tuneinbrowser.data.TuneInResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface TuneInBrowserService {
    @GET("/")
    suspend fun basePage() : TuneInResponse

    @GET
    suspend fun customUrl(@Url endpoint: String): TuneInResponse
}