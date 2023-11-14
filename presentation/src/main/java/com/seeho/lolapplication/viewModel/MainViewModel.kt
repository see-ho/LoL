package com.seeho.lolapplication.viewModel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.seeho.data.remote.model.Champion
import com.seeho.domain.Resource
import com.seeho.domain.model.DomainChampion
import com.seeho.domain.model.SaveableChampionsResource
import com.seeho.domain.useCase.GetBookmarkedChampionIdsUseCase
import com.seeho.domain.useCase.GetChampionUseCase
import com.seeho.lolapplication.uiState.ChampionsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getChampionUseCase: GetChampionUseCase,
    private val getBookmarkedChampionIdsUseCase: GetBookmarkedChampionIdsUseCase
): ViewModel() {
    private val _combineUiState = MutableStateFlow<CombineUiState>(CombineUiState.Loading)
    val combineUiState: StateFlow<CombineUiState> = _combineUiState.asStateFlow()

    init {
        viewModelScope.launch {
            Log.e("TAG", ":  ${getChampionUseCase()[1]}", )
            val championsFlow = flow { emit(getChampionUseCase()) }
            val bookmarkedIdsFlow = getBookmarkedChampionIdsUseCase()

            championsFlow.combine(bookmarkedIdsFlow) { champions, bookmarkedIds ->
                val enhancedChampions = champions.map { champion ->
                    val isBookmarked = bookmarkedIds.contains(champion.id)
                    SaveableChampionsResource(champion,isBookmarked)
                }
                CombineUiState.Champions(
                    champions = enhancedChampions
                )
            }.catch { throwable ->
                _errorFlow.emit(throwable)
            }.collect { combinedUiState ->
                _combineUiState.value = combinedUiState
            }
        }
    }
//    init {
//        viewModelScope.launch {
//            GetBookmarked().collectLatest{
//                Log.e("나는성공", "$it: ", )
//            }
//            combine(
//                combineUiState,
//                GetBookmarked()
//            ){ combineUiState,bookmarkChampions ->
//                when(combineUiState) {
//                    is CombineUiState.Loading ->{
//                        CombineUiState.Champions(
//                            champions = bookmarkChampions
//                        )
//                    }
//                    is CombineUiState.Champions -> {
//                        combineUiState.copy(
//                            champions = bookmarkChampions
//                        )
//                    }
//                }
//            }.catch { throwable ->
//                _errorFlow.emit(throwable)
//            }.collect{
//                _combineUiState.value = it
//            }
//
//        }
//    }
//    private suspend fun GetBookmarked(): Flow<List<SaveableChampionsResource>> {
//        return flow {
//            emit(getChampionUseCase())
//        }.combine(getBookmarkedChampionIdsUseCase()) { champion, book ->
//            champion.map { SaveableChampionsResource(it, book.contains(it.id)) }
//        }.catch {
//            _errorFlow.emit(it)
//        }


    fun toggleBookmarkButton(id:String, isBookmarked: Boolean){
        viewModelScope.launch {
            getBookmarkedChampionIdsUseCase.toggle(id,isBookmarked)
        }
    }
    init {
        //getChampions()
    }
    val champion : LiveData<DomainChampion>
        get() = _champion
    private var _champion : MutableLiveData<DomainChampion> = MutableLiveData()
    fun setChampion(it: DomainChampion){
        _champion.value = it
    }


    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow


    private val _uiState = MutableStateFlow<ChampionsUiState>(ChampionsUiState.Loading)
    val uiState: StateFlow<ChampionsUiState> = _uiState

    //      ver 1. 성-공??
//    val uiState: StateFlow<ChampionsUiState> =
//        flow {
//            val champions = getChampionUseCase()
//            val uiState = ChampionsUiState.Champions(champions)
//            emit(uiState)
//        }
//            .catch { throwable -> _errorFlow.emit(throwable) }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(5_000),
//                initialValue = ChampionsUiState.Loading,
//            )

//    fun getChampions(){
//        viewModelScope.launch {
//            uiState.collect{ state->
//                when(state){
//                    is ChampionsUiState.Loading -> {  }
//                    is ChampionsUiState.Champions -> {
//                        Log.e("겟챰피온", "getChampions: ${state.champions}", )
//                    }
//
//                    else -> {}
//                }
//            }
//        }
//    }






    //combine ver


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

}
sealed interface CombineUiState{
    object Loading : CombineUiState
    data class Champions(
        val champions: List<SaveableChampionsResource>
    ) : CombineUiState
}