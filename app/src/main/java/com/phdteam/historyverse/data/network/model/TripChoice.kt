package com.phdteam.historyverse.data.network.model


import com.google.gson.annotations.SerializedName

data class TripChoice(
    @SerializedName("cities")
    val cities: List<String?>?,
    @SerializedName("start_location")
    val startLocation: String?
)