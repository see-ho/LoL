package com.seeho.data.local.datasource

import kotlinx.coroutines.flow.Flow

interface BookmarkDataSource {
    val bookmarkedChampion: Flow<Set<String>>
    suspend fun updateBookmarkedChampion(bookmarkedChampion: Set<String>)
}