package com.apripachkin.tuneinbrowser.di

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class JsonRendererInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url()
        val build = url.newBuilder()
            .addQueryParameter("render", "json")
            .build()
        val modifiedUrl = originalRequest
            .newBuilder()
            .url(build)
            .build()
        return chain.proceed(modifiedUrl)
    }
}