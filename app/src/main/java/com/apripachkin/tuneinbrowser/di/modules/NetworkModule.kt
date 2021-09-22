package com.apripachkin.tuneinbrowser.di.modules

import android.content.Context
import com.apripachkin.tuneinbrowser.R
import com.apripachkin.tuneinbrowser.data.TuneInBrowserService
import com.apripachkin.tuneinbrowser.di.JsonRendererInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideRetrofit(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient
    ): TuneInBrowserService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(context.getString(R.string.base_url))
            .build()
            .create(TuneInBrowserService::class.java)
    }

    @Provides
    fun provideOkHttpClient(jsonRendererInterceptor: JsonRendererInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(jsonRendererInterceptor)
            .connectTimeout(2, TimeUnit.SECONDS)
            .build()
    }
}