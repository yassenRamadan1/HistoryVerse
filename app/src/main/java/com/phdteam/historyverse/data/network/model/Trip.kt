package com.phdteam.historyverse.data.network.model


import com.google.gson.annotations.SerializedName

data class Trip(
    @SerializedName("trip_plan")
    val tripPlan: List<TripPlan?>?
) {
    data class TripPlan(
        @SerializedName("city")
        val city: String?,
        @SerializedName("distance")
        val distance: Int?,
        @SerializedName("imageUrl")
        val imageUrl: String?,
        @SerializedName("latitude")
        val latitude: Int?,
        @SerializedName("longitude")
        val longitude: Int?,
        @SerializedName("name")
        val name: String?
    )
}