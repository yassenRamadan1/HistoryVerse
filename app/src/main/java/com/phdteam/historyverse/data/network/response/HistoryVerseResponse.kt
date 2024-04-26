package com.phdteam.historyverse.data.network.response

import com.google.gson.annotations.SerializedName

data class HistoryVerseResponse(
    @SerializedName("user_id")
    val id: String,
)
