package com.seeho.lolapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.seeho.domain.Resource
import com.seeho.domain.model.DomainChampion
import com.seeho.domain.useCase.GetChampionUseCase
import com.seeho.lolapplication.uiState.ChampionsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach


@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val getChampionUseCase: GetChampionUseCase
): ViewModel() {

//      ver 1. 성-공??
    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    val uiState: StateFlow<ChampionsUiState> =
        flow {
            val champions = getChampionUseCase()
            val uiState = ChampionsUiState.Champions(champions)
            emit(uiState)
            }
            .catch { throwable -> _errorFlow.emit(throwable) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ChampionsUiState.Loading,
            )

    //야는 진짜 안됨.
    /*
    val uiState: StateFlow<ChampionsUiState> =
    flow { emit(getChampionUseCase().toPersistentList()) }
        .map { ChampionsUiState::Champions }
        .catch { throwable -> _errorFlow.emit(throwable) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ChampionsUiState.Loading,
        ) as StateFlow<ChampionsUiState> */


    fun getChampions(){
        viewModelScope.launch {
            uiState.collect{ state->
                when(state){
                    is ChampionsUiState.Loading -> {  }
                    is ChampionsUiState.Champions -> {
                        Log.e("겟챰피온", "getChampions: ${state.champions}", )
                    }
                }
            }
        }
    }


//     ver 2. 성공
    data class UiState(
        val isLoading: Boolean = false,
        val champions: List<DomainChampion> = emptyList(),
        val error: String = ""
    )

//    private val _uiState = MutableStateFlow<UiState>(UiState(isLoading = true))
//    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

//    val liveData: LiveData<UiState> = uiState.asLiveData()

//    fun getChampions(){
//        getChampionUseCase().onEach { result ->
//            _uiState.value = when(result){
//                is Resource.Success -> {
//                    UiState(
//                        champions = result.data ?: emptyList(),
//                        isLoading = false)
//                }
//                is Resource.Error -> {
//                    UiState(error = result.message)
//                }
//
//                is Resource.Loading -> {
//                    UiState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
    init {
        getChampions()
    }
}
