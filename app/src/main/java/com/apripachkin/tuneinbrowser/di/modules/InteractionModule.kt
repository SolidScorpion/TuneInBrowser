package com.apripachkin.tuneinbrowser.di.modules

import com.apripachkin.tuneinbrowser.domain.interactor.RemoteServiceInteractor
import com.apripachkin.tuneinbrowser.domain.interactor.RemoteServiceInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class InteractionModule {
    @Binds
    abstract fun provideRemoteInteractor(impl: RemoteServiceInteractorImpl): RemoteServiceInteractor
}