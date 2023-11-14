package com.seeho.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.seeho.data.local.datasource.BookmarkDataSource
import com.seeho.data.local.datasourceImpl.BookmarkDataSourceImpl
import com.seeho.data.remote.dataSource.ChampionApi
import com.seeho.data.remote.dataSourceImpl.ChampionApiImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    private const val BOOKMARK_DATASTORE_NAME = "BOOKMARK_PREFERENCES"
    private val Context.sessionDataStore by preferencesDataStore(BOOKMARK_DATASTORE_NAME)
    @Provides
    @Singleton
    fun provideChampionApi(
        retrofit: Retrofit
    ): ChampionApi{
        return ChampionApiImpl(retrofit)
    }

    @Provides
    @Singleton
    @Named("bookmark")
    fun provideBookmarkDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.sessionDataStore

    @Provides
    @Singleton
    fun provideBookmarkDataSource(
        @Named("bookmark")
        dataStore: DataStore<Preferences>
    ): BookmarkDataSource {
        return BookmarkDataSourceImpl(dataStore)
    }


}
