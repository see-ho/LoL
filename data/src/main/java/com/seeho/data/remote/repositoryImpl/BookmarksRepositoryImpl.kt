package com.seeho.data.remote.repositoryImpl

import com.seeho.data.local.datasource.BookmarkDataSource
import com.seeho.domain.repository.BookmarksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class BookmarksRepositoryImpl @Inject constructor (
    private val localDataStore: BookmarkDataSource
) : BookmarksRepository {
    val bookmarkIds: Flow<Set<String>> = localDataStore.bookmarkedChampion
    override suspend fun getBookmarkedChampionIds(): Flow<Set<String>> {
        return bookmarkIds.filterNotNull()
    }

    override suspend fun bookmarkChampion(sessionId: String, bookmark: Boolean) {
        val currentBookmarkedChampionIds = bookmarkIds.first()
        localDataStore.updateBookmarkedChampion(
            if (bookmark) {
                currentBookmarkedChampionIds + sessionId
            } else {
                currentBookmarkedChampionIds - sessionId
            }
        )
    }
}