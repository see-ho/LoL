package com.seeho.data.di

import com.seeho.data.remote.dataSource.ChampionApi
import com.seeho.data.remote.repositoryImpl.ChampionRepositoryImpl
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
}