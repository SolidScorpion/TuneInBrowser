package com.apripachkin.tuneinbrowser.di.modules

import com.apripachkin.tuneinbrowser.utils.image.ImageLoader
import com.apripachkin.tuneinbrowser.utils.image.ImageLoaderImpl
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