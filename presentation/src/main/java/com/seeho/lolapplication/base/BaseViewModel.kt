package com.seeho.lolapplication.base

import android.database.sqlite.SQLiteConstraintException
import android.net.http.HttpException
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.*
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseViewModel(val name: String) : ViewModel(){
    /*init {
        Log.e("TAG", "$name 생성", )
    }*/
    protected val isLoading = MutableLiveData(false)

    private val job = SupervisorJob()

    protected val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e("TAG", "@@@@ $throwable", )
            Log.e("TAG", "@@@@ ${throwable.message}")
            Log.e("TAG", "@@@@ ${throwable.cause}")
            Log.e("TAG", "@@@@ ${throwable.suppressedExceptions}")
            Log.e("TAG", "@@@@ ${throwable.printStackTrace()}")
            Log.e("TAG", "@@@@ ${throwable.localizedMessage}")
            Log.e("TAG", "@@@@ ${throwable.suppressed}")
        isLoading.postValue(false)
        coroutineContext.job.cancel()
//        throwable.printStackTrace()
//        when(throwable){
//            is SocketException -> _fetchState.value = Pair(throwable, FetchState.BAD_INTERNET)
//            is HttpException -> _fetchState.value = Pair(throwable, FetchState.PARSE_ERROR)
//            is UnknownHostException -> _fetchState.value = Pair(throwable, FetchState.WRONG_CONNECTION)
//            is SQLiteConstraintException -> _fetchState.value = Pair(throwable, FetchState.SQLITE_CONSTRAINT_PRIMARYKEY)
//            is SocketTimeoutException -> _fetchState.value = Pair(throwable, FetchState.SOCKET_TIMEOUT_EXCEPTION)
//            is IllegalStateException -> _fetchState.value = Pair(throwable, FetchState.IllegalStateException)
//            else -> _fetchState.value = Pair(throwable, FetchState.FAIL)
//        }
//        Log.e("TAG", "base err fetchState.value : ${fetchState.value}", )
    }

    protected val modelScope = viewModelScope + job + exceptionHandler
    protected val ioScope = CoroutineScope(Dispatchers.IO) + job + exceptionHandler

    override fun onCleared() {
        super.onCleared()
        Log.e("TAG", "$name 삭제", )
    }
}