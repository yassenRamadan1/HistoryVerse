package com.phdteam.historyverse.data.network.model


import com.google.gson.annotations.SerializedName

data class TripMuseum(
    @SerializedName("city")
    val city: String?,
    @SerializedName("distance")
    val distance: Double?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?,
    @SerializedName("name")
    val name: String?
)