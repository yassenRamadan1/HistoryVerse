package com.phdteam.historyverse.data.network.model


import com.google.gson.annotations.SerializedName

data class Artifact(
    @SerializedName("artifact_Description")
    val artifactDescription: String?,
    @SerializedName("artifact_History")
    val artifactHistory: String?,
    @SerializedName("artifact_ImageUrl")
    val artifactImageUrl: String?,
    @SerializedName("artifact_Type")
    val artifactType: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("museumId")
    val museumId :Int?,
)