package com.seeho.data.di

import com.seeho.data.local.datasource.BookmarkDataSource
import com.seeho.data.remote.dataSource.ChampionApi
import com.seeho.data.remote.repositoryImpl.BookmarksRepositoryImpl
import com.seeho.data.remote.repositoryImpl.ChampionRepositoryImpl
import com.seeho.domain.repository.BookmarksRepository
import com.seeho.domain.repository.ChampionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideChampionRepository(
        api: ChampionApi
    ): ChampionRepository {
        return ChampionRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideBookmarksRepository(
        localDataStore: BookmarkDataSource
    ): BookmarksRepository {
        return BookmarksRepositoryImpl(localDataStore)
    }
}