package com.seeho.domain.useCase

import com.seeho.domain.Resource
import com.seeho.domain.repository.ChampionRepository
import com.seeho.domain.model.DomainChampion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetChampionUseCase(
    private val championRepository: ChampionRepository,
) {
     //ver 1
    suspend operator fun invoke(): List<DomainChampion> {
        return championRepository.getChampions()
    }

//    ver2. 성공
//    operator fun invoke(): Flow<Resource<List<DomainChampion>>> = flow{
//        try {
//            emit(Resource.Loading<List<DomainChampion>>())
//            val champions = championRepository.getChampions()
//            emit(Resource.Success<List<DomainChampion>>(champions))
//        } catch (e: Exception){
//            emit(Resource.Error<List<DomainChampion>>(e.localizedMessage))
//        }
//    }
}