package com.spydevs.fiestonvirtual.framework.socketio

import com.spydevs.fiestonvirtual.ui.main.chat.ChatMessage

sealed class SocketIOResult {

    sealed class Connection : SocketIOResult() {
        data class Success(val status: Boolean) : Connection()
        data class Loading(var loading: Boolean) : Connection()
        data class Timeout(var loading: Boolean) : Connection()
        data class Error(var loading: Boolean) : Connection()
        data class Disconnect(var loading: Boolean) : Connection()
    }

    sealed class ReceiveIncomingMessage : SocketIOResult() {
        data class Success(var chatMessage: ChatMessage) : ReceiveIncomingMessage()
    }
}