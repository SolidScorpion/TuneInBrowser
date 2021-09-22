package com.apripachkin.tuneinbrowser.data

import retrofit2.http.GET

interface TuneInBrowserService {
    @GET("/")
    suspend fun basePage() : Response
}