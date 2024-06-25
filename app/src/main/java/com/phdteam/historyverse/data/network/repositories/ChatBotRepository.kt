package com.phdteam.historyverse.data.network.repositories

interface ChatBotRepository {
    suspend fun sendText(question: String, speak: Boolean = false): String
    suspend fun sendAudio(audioURL: String): String
    suspend fun uploadAudio(audioUri: String): String
}