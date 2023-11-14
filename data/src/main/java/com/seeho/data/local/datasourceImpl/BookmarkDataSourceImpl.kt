package com.seeho.data.local.datasourceImpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.seeho.data.local.datasource.BookmarkDataSource
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class BookmarkDataSourceImpl @Inject constructor(
    @Named("bookmark") private val dataStore: DataStore<Preferences>,
) : BookmarkDataSource {
    object PreferencesKey {
        val BOOKMARKED_SESSION = stringSetPreferencesKey("BOOKMARKED_SESSION")
    }

    override val bookmarkedChampion = dataStore.data.map {
        it[PreferencesKey.BOOKMARKED_SESSION] ?: emptySet()
    }

    override suspend fun updateBookmarkedChampion(bookmarkedChampion: Set<String>) {
        dataStore
            .edit {
                it[PreferencesKey.BOOKMARKED_SESSION] = bookmarkedChampion
            }
    }


}