package com.seeho.lolapplication.uiState

import com.seeho.data.remote.model.Champion
import androidx.compose.runtime.Stable
import com.seeho.domain.model.DomainChampion
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf


sealed interface ChampionsUiState {
    object Loading : ChampionsUiState
    data class Champions(
        val champions: List<DomainChampion>
    ) : ChampionsUiState
}