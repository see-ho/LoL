package com.seeho.domain.useCase

import com.seeho.domain.repository.BookmarksRepository
import kotlinx.coroutines.flow.Flow

class GetBookmarkedChampionIdsUseCase(
    private val bookmarksRepository: BookmarksRepository
) {
    suspend operator fun invoke(): Flow<Set<String>> {
        return bookmarksRepository.getBookmarkedChampionIds()
    }

    suspend fun toggle(id: String, bookmark: Boolean) {
        return bookmarksRepository.bookmarkChampion(id, bookmark)
    }
}