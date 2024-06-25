package com.phdteam.historyverse.data.network.repositories

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.phdteam.historyverse.data.network.model.ChatBotRequestBody
import com.phdteam.historyverse.data.network.model.ChatBotVoiceBody
import com.phdteam.historyverse.data.network.service.ChatBotService
import kotlinx.coroutines.tasks.await
import java.util.UUID

class ChatBotRepositoryImpl(
    private val chatBotService: ChatBotService,
    private val firebaseStorage: FirebaseStorage
) : ChatBotRepository {
    override suspend fun sendText(question: String, speak: Boolean): String {
        return try {
            val request =
                chatBotService
                    .sendTextToBot(
                        ChatBotRequestBody(
                            query = question,
                            speak = speak,
                        )
                    )
            if (request.isSuccessful || !request.body()?.Answer.isNullOrBlank())
                request.body()?.Answer.toString()
            else
                "error connecting"
        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override suspend fun sendAudio(audioURL: String): String {
        val request =
            chatBotService.sendVoiceToBot(ChatBotVoiceBody(audio_file_path = audioURL, speak = false))
        return if (request.isSuccessful)
            request.body()?.Answer.toString()
        else "error connecting"
    }

    override suspend fun uploadAudio(audioUri: String): String {
        val storageRef = firebaseStorage.reference.child("${"users audio"}/${UUID.randomUUID()}")
        storageRef.putFile(Uri.parse(audioUri)).await()
        val downloadUrl = storageRef.downloadUrl.await()
        return downloadUrl.toString()
    }
}