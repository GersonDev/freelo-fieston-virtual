package com.spydevs.fiestonvirtual.ui.main.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.message.GetChatMessagesUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.message.SendChatMessageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatViewModel(
    private val getMessagesUseCase: GetChatMessagesUseCase,
    private val sendChatMessageUseCase: SendChatMessageUseCase
) : ViewModel() {

    private val _chatResult = MutableLiveData<ChatResult>()
    val chatResult: LiveData<ChatResult>
        get() = _chatResult

    fun getMessages() {
        viewModelScope.launch(Dispatchers.Main) {
            _chatResult.value = ChatResult.GetMessages.Loading(true)
            when (val result = getMessagesUseCase()) {
                is ResultType.Success -> {
                    _chatResult.value = ChatResult.GetMessages.Success(result.value)
                }
                is ResultType.Error -> {
                    _chatResult.value = ChatResult.GetMessages.Error(result.value.message)
                }
            }
            _chatResult.value = ChatResult.GetMessages.Loading(false)
        }
    }

    fun sendMessage(chatMessage: String) {
        if (chatMessage.isEmpty()) {
            _chatResult.value = (ChatResult.SendOutgoingMessage.Error("Mensaje Vacío"))
            return
        }

        viewModelScope.launch(Dispatchers.Main) {
            val sentChatMessage = sendChatMessageUseCase(chatMessage)
            _chatResult.value = (ChatResult.SendOutgoingMessage.Success(sentChatMessage))
        }

    }

}