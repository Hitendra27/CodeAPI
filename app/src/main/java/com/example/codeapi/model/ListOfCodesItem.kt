package com.example.codeapi.model


import com.google.gson.annotations.SerializedName

data class ListOfCodesItem(
    @SerializedName("duration")
    val duration: String,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("in_24_hours")
    val in24Hours: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("url")
    val url: String
)