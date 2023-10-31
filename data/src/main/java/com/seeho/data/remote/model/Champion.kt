package com.seeho.data.remote.model

import com.seeho.domain.model.DomainChampion

data class Champion(
    val id: String,
    val key: String,
    val name: String,
    val title: String,
    val blurb: String,
){
    fun toDomainChampion(): DomainChampion {
        return DomainChampion(
            id, key, name, title, blurb
        )
    }
}
