package com.phdteam.historyverse.data.network.service

import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content


class GeminiApi(private val model: GenerativeModel) {

    /**
     * Generates a chat using the provided user role and model role.
     *
     * @param userRole The role of the user in the chat.
     * @param modelRole The role of the model in the chat.
     * @return A chat generated by the model.
     */
    fun generateContent(userRole: String, modelRole: String): Chat {
        return model.startChat(listOf(
            content(role = "user") { text(userRole) },
            content(role = "model") { text(modelRole) }
        ))

    }

}