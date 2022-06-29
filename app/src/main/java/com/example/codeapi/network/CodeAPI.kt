package com.example.codeapi.network

import androidx.transition.Slide
import com.example.codeapi.model.ListOfCodes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CodeAPI {

    @GET(ACTIVE_ALERTS)
    suspend fun getListOfCodes(
        @Query("status")status:String="actual",
        @Query("message_type")type: String="alert"
    ): Response<ListOfCodes>

    companion object {
        const val BASE_URL = "https://kontests.net/api/"
        private const val ACTIVE_ALERTS = "v1/all"
    }
}

// Api = "https://kontests.net/api/v1/all"