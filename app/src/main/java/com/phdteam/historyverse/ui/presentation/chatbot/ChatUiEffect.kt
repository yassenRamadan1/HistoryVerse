package com.phdteam.historyverse.ui.presentation.chatbot

sealed interface ChatUIEffect {
    object Error : ChatUIEffect
}