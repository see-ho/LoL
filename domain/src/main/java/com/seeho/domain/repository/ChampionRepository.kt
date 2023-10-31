package com.seeho.domain.repository

import com.seeho.domain.model.DomainChampion

interface ChampionRepository {
    suspend fun getChampions(): List<DomainChampion>
}