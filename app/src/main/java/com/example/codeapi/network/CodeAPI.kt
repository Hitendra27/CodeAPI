package com.example.codeapi.network

import androidx.transition.Slide
import retrofit2.http.GET
import retrofit2.http.Query

interface CodeAPI {

    @GET(ACTIVE_ALERTS)
    suspend fun getCodeList(
        @Query("status")status.String="actual",
        @Query("message_type")type: String="alert"
    ): Response<>

    companion object {
        const val BASE_URL = "https://kontests.net/api/"
        private const val ACTIVE_ALERTS = "v1/all"
    }
}

// Api = "https://kontests.net/api/v1/all"