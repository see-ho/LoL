package com.seeho.data.remote.dataSourceImpl

import com.seeho.data.remote.dataSource.ChampionApi
import com.seeho.data.remote.model.Champion
import com.seeho.data.remote.model.ChampionResponse
import retrofit2.Retrofit
import javax.inject.Inject

class ChampionApiImpl @Inject constructor(
    private val retrofit: Retrofit
): ChampionApi {
    override suspend fun getChampions(): ChampionResponse<Champion> {
        return retrofit.create(ChampionApi::class.java).getChampions()
    }

}