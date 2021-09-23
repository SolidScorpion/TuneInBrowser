package com.apripachkin.tuneinbrowser.data.service

import com.apripachkin.tuneinbrowser.data.Response
import retrofit2.http.GET

interface TuneInBrowserService {
    @GET("/")
    suspend fun basePage() : Response
}