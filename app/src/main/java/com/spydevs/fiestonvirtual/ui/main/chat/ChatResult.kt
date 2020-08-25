package com.spydevs.fiestonvirtual.ui.main.chat

sealed class ChatResult {
    sealed class SendOutgoingMessage : ChatResult() {
        data class Success(val chatMessage: ChatMessage) : SendOutgoingMessage()
        data class Error(var errorMessage: String) : SendOutgoingMessage()
    }
}