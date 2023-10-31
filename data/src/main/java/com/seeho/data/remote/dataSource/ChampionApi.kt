package com.seeho.data.remote.dataSource

import com.seeho.data.remote.model.Champion
import com.seeho.data.remote.model.ChampionResponse
import retrofit2.http.GET

interface ChampionApi {
    @GET("data/ko_KR/champion.json")
    suspend fun getChampions(): ChampionResponse<Champion>
}