package com.phdteam.historyverse.data.network.model


import com.google.gson.annotations.SerializedName

data class Museum(
    @SerializedName("city")
    val city: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?,
    @SerializedName("museum_Description")
    val museumDescription: String?,
    @SerializedName("museum_History")
    val museumHistory: String?,
    @SerializedName("museumId")
    val museumId: Int?,
    @SerializedName("museum_Type")
    val museumType: String?,
    @SerializedName("name")
    val name: String?
)