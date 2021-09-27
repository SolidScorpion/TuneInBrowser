package com.apripachkin.tuneinbrowser.di.modules

import android.content.Context
import com.apripachkin.tuneinbrowser.R
import com.apripachkin.tuneinbrowser.data.adapter.OutLineResponseAdapter
import com.apripachkin.tuneinbrowser.data.models.OutLineType
import com.apripachkin.tuneinbrowser.data.service.JsonRendererInterceptor
import com.apripachkin.tuneinbrowser.data.service.TuneInBrowserService
import com.squareup.moshi.Moshi
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
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(OutLineType::class.java, OutLineResponseAdapter()).build()
                )
            )
            .baseUrl(context.getString(R.string.base_url))
            .build()
            .create(TuneInBrowserService::class.java)
    }

    @Provides
    fun provideOkHttpClient(jsonRendererInterceptor: JsonRendererInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(jsonRendererInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }
}
