package com.seeho.data.di

import com.seeho.domain.repository.ChampionRepository
import com.seeho.domain.useCase.GetChampionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetChampionUseCase(repository: ChampionRepository): GetChampionUseCase {
        return GetChampionUseCase(repository)
    }
}