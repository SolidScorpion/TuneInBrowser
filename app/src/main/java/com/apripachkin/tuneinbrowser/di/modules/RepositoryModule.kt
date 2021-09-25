package com.apripachkin.tuneinbrowser.di.modules

import com.apripachkin.tuneinbrowser.domain.repo.RemoteRepoImpl
import com.apripachkin.tuneinbrowser.domain.repo.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRemoteRepo(impl: RemoteRepoImpl): RemoteRepository
}