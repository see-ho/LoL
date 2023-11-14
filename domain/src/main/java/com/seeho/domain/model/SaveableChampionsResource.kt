package com.seeho.domain.model

data class SaveableChampionsResource(
    val champion: DomainChampion,
    val isBookmarked: Boolean
)
