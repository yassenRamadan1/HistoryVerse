package com.phdteam.historyverse.data.network.service

import com.phdteam.historyverse.data.network.model.ChatBotRequestBody
import com.phdteam.historyverse.data.network.model.ChatBotVoiceBody
import com.phdteam.historyverse.data.network.response.ChatBotResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatBotService {

    @POST("text_convo")
    suspend fun sendTextToBot(@Body body: ChatBotRequestBody): Response<ChatBotResponse>

    @POST("voice_convo")
    suspend fun sendVoiceToBot(@Body body: ChatBotVoiceBody): Response<ChatBotResponse>
}