package com.phdteam.historyverse.ui.presentation.chatbot

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.type.content
import com.phdteam.historyverse.data.network.repositories.ChatBotRepository
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException

class ChatBotViewModel(
    private val mentorRepository: HistoryVerseRepository,
    private val chatBotRepository: ChatBotRepository
) : BaseViewModel<ChatUiState, ChatUIEffect>(ChatUiState()) {

    private var mediaRecorder: MediaRecorder? = null
    private var audioFile: File? = null

    fun setRoles(user: String, model: String) {
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
//        val oldMsg = state.value.message
//        val messages = state.value.messages.toMutableList()
//        messages.add(MessageUIState(isMe = true, message = oldMsg))
//        updateState { it.copy(messages = messages, message = "") }
//        getData(oldMsg)
        sendMessage()
    }


    fun onChanceMessage(newValue: String) {
        updateState { it.copy(message = newValue) }
    }

    fun sendAudio(filePath: String) {
//        Log.d("Audio", filePath)
        viewModelScope.launch {
            chatBotRepository.uploadAudio(filePath).also { response ->
                chatBotRepository.sendAudio(response).also { answer ->
                    updateState {
                        it.copy(
                            messages = it.messages + MessageUIState(
                                message = answer,
                                isMe = false
                            ),
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun sendMessage() {
        viewModelScope.launch {
            val message = state.value.message
            updateState {
                it.copy(
                    message = "",
                    messages = it.messages + MessageUIState(message = message, isMe = true),
                    isLoading = true
                )
            }
            chatBotRepository.sendText(message).also { response ->
                updateState {
                    it.copy(
                        messages = it.messages + MessageUIState(message = response, isMe = false),
                        isLoading = false
                    )
                }
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun onRecordClick(context: Context, audioFilePath: File) {
        Log.d("isRecording", state.value.isRecording.toString())
        if (state.value.isRecording) {
            stopRecording()
        } else {
            audioFile = audioFilePath
            startRecording(context)
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun startRecording(context: Context) {
        mediaRecorder = MediaRecorder(context).apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(audioFile)
            try {
                prepare()
                start()
                updateState { it.copy(isRecording = true) }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun stopRecording() {
        updateState {
            it.copy(
                isRecording = false,
                isLoading = true,
                messages = it.messages + MessageUIState(
                    message = "Audio message sent",
                    isMe = true
                )
            )
        }
        mediaRecorder?.apply {
            try {
                stop()
                release()
                audioFile?.let { sendAudio(it.toUri().toString()) }
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }
        }
        mediaRecorder = null
    }

}