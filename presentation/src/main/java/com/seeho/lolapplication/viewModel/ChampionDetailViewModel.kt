package com.seeho.lolapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Random
import javax.inject.Inject

@HiltViewModel
class ChampionDetailViewModel @Inject constructor(): ViewModel(){
    private val _stringList = MutableStateFlow<List<String>>(emptyList())
    val stringList: StateFlow<List<String>> = _stringList.asStateFlow()

    init {
        generateStrings()
    }

    private fun generateStrings() {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                val newString = generateRandomString()
                _stringList.value = _stringList.value + listOf(newString)

                // 3초 대기
                delay(2000)
            }
        }
    }

    private fun generateRandomString(): String {
        return when(Random().nextInt(3)){
            0-> "이번 스킨 예쁘다"
            1 -> "상대법 좀 알려주세요"
            else-> "얘 왜 너프 안 함?"
        }
    }
}