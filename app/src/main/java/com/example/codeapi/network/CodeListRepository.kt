package com.example.codeapi.network

import com.example.codeapi.utils.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import java.lang.IllegalStateException

interface CodeListRepository {
    val responseFlow: StateFlow<UIState>
    suspend fun getActiveAlerts()
}

class CodeListRepositoryImpl(
    private val codeAPI: CodeAPI
) : CodeListRepository {

    private val _responseFlow: MutableStateFlow<UIState> = MutableStateFlow(UIState.LOADING())

    override val responseFlow: StateFlow<UIState>
    get() = _responseFlow

    override suspend fun getActiveAlerts() {
        try {
            val response = codeAPI.getListOfCodes()

            if (response.isSuccessful) {
                response.body()?.let {
                    _responseFlow.value = UIState.SUCCESS(it)
                } ?: run {
                    _responseFlow.value =
                        UIState.ERROR(IllegalStateException("Codes are null"))
                }
            } else {
                _responseFlow.value = UIState.ERROR(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception){
            _responseFlow.value = UIState.ERROR(e)
        }
    }
}