package com.apripachkin.tuneinbrowser.di.modules

import com.apripachkin.tuneinbrowser.ui.ImageLoader
import com.apripachkin.tuneinbrowser.ui.ImageLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilLibsModule {
    @Binds
    abstract fun provideImageLoader(impl: ImageLoaderImpl): ImageLoader
}