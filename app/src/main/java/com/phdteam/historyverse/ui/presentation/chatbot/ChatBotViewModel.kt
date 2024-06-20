package com.phdteam.historyverse.ui.presentation.chatbot

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.type.content
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ChatBotViewModel(
    private val mentorRepository: HistoryVerseRepository
) : BaseViewModel<ChatUiState, ChatUIEffect>(ChatUiState()) {

    fun setRoles(user:String, model: String) {
        updateState { it.copy(userRole = user, modelRole = model) }
    }
    private fun getData(msg: String) {
        val chat = mentorRepository.generateContent(
            userContent = state.value.userRole, modelContent = state.value.modelRole
        )
        val completeText = StringBuilder()
        val messages = state.value.messages.toMutableList()
        messages.add(MessageUIState(isMe = true, message = ""))
        viewModelScope.launch {
            chat.sendMessageStream(msg).map { it.text ?: "" }
                .onEach { completeText.append(it) }
                .onStart { updateState { it.copy(canNotSendMessage = true) } }
                .onCompletion {
                    updateState { it.copy(canNotSendMessage = false) }
                }.catch { e ->
                    updateState { it.copy(canNotSendMessage = true) }
                    Log.e("ChatBotViewModel", "getData: ${e.message}")
                    sendNewEffect(ChatUIEffect.Error)
                }
                .collectLatest { reply ->
                    chat.history.add(content(role = "user") { text(msg) })
                    chat.history.add(content(role = "model") { text(completeText.toString()) })
                    val old = messages.last().message + " " + reply
                    messages.removeLast()
                    messages.add(MessageUIState(isMe = false, message = old))
                    updateState { it.copy(messages = messages) }
                }
        }
    }


    fun onSendClicked() {
        val oldMsg = state.value.message
        val messages = state.value.messages.toMutableList()
        messages.add(MessageUIState(isMe = true, message = oldMsg))
        updateState { it.copy(messages = messages, message = "") }
        getData(oldMsg)
    }


    fun onChanceMessage(newValue: String) {
        updateState { it.copy(message = newValue) }
    }

}