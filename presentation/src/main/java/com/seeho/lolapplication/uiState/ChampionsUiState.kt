package com.seeho.lolapplication.uiState

import com.seeho.data.remote.model.Champion
import androidx.compose.runtime.Stable
import com.seeho.domain.model.DomainChampion
import kotlinx.collections.immutable.PersistentList


sealed interface ChampionsUiState {
    object Loading : ChampionsUiState
    @Stable
    data class Champions(
        val champions: List<DomainChampion>
    ) : ChampionsUiState
}