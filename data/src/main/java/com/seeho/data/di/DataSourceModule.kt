package com.seeho.data.di

import com.seeho.data.remote.dataSource.ChampionApi
import com.seeho.data.remote.dataSourceImpl.ChampionApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideChampionApi(
        retrofit: Retrofit
    ): ChampionApi{
        return ChampionApiImpl(retrofit)
    }
}