package com.seeho.data.remote.repositoryImpl

import com.seeho.data.remote.dataSource.ChampionApi
import com.seeho.domain.model.DomainChampion
import com.seeho.domain.repository.ChampionRepository
import javax.inject.Inject

internal class ChampionRepositoryImpl @Inject constructor(
    private val championApi: ChampionApi
) : ChampionRepository {
    override suspend fun getChampions(): List<DomainChampion> {
        return championApi.getChampions().toList()
            .map { it.toDomainChampion() }
    }
}