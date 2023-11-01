package com.seeho.data.remote.model

import com.seeho.domain.model.DomainChampion
import com.seeho.domain.model.Skin

data class Champion(
    val id: String,
    val key: String,
    val name: String,
    val title: String,
    val blurb: String,
    //val skins: List<Skin>
){
    fun toDomainChampion(): DomainChampion {
        return DomainChampion(
            id, key, name, title, blurb, /*skins*/
        )
    }
}
