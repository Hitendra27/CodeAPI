package com.example.codeapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codeapi.network.CodeListRepository
import com.example.codeapi.utils.UIState
import kotlinx.coroutines.*

class CodeListViewModel (private  val codeListRepository: CodeListRepository,
                         private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
                         private val coroutineScope: CoroutineScope = CoroutineScope(
                             SupervisorJob() + ioDispatcher)
) : CoroutineScope by coroutineScope, ViewModel() {

    private val _codeListLiveData: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING())
    val codeListLiveData: LiveData<UIState> get() = _codeListLiveData

    fun subcribleToCodeListInfo() {
        _codeListLiveData.postValue(UIState.LOADING())

        collectCodeListInfo()

        launch {
            codeListRepository.getActiveAlerts()
        }
    }

    private fun collectCodeListInfo() {
        launch {
            codeListRepository.responseFlow.collect { uiState ->
                when(uiState) {
                    is UIState.LOADING -> { _codeListLiveData.postValue(uiState) }
                    is UIState.SUCCESS -> { _codeListLiveData.postValue(uiState) }
                    is UIState.ERROR -> { _codeListLiveData.postValue(uiState) }
                }

            }
        }
    }
}