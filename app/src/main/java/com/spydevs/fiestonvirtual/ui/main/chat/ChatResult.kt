package com.spydevs.fiestonvirtual.ui.main.chat

import com.spydevs.fiestonvirtual.domain.models.message.ChatMessage

sealed class ChatResult {

    sealed class GetMessages : ChatResult() {
        data class Success(val messages: List<ChatMessage>) : GetMessages()
        data class Error(var errorMessage: String) : GetMessages()
        data class Loading(var loading: Boolean) : GetMessages()
    }

    sealed class SendOutgoingMessage : ChatResult() {
        data class Success(val chatMessage: ChatMessage) : SendOutgoingMessage()
        data class Error(var errorMessage: String) : SendOutgoingMessage()
    }
}