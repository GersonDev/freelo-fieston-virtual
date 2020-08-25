package com.spydevs.fiestonvirtual.ui.main.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChatViewModel : ViewModel() {

    private val _chatResult = MutableLiveData<ChatResult>()
    val chatResult: LiveData<ChatResult>
        get() = _chatResult

    fun sendMessage(message: String) {
        if (message.isEmpty()) {
            _chatResult.value = (ChatResult.SendOutgoingMessage.Error("Mensaje Vacío"))
            return
        }
        val outgoingMessage = ChatMessage(message, ChatMessageViewType.OUTGOING)
        _chatResult.value = (ChatResult.SendOutgoingMessage.Success(outgoingMessage))
    }

}