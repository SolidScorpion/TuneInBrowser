package com.apripachkin.tuneinbrowser.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {
    @Dispatcher.Main
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Dispatcher.IO
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Dispatcher.Default
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Dispatcher {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Main

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class IO

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Default
}