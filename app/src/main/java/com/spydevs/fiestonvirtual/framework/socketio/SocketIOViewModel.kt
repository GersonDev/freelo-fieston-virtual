package com.spydevs.fiestonvirtual.framework.socketio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.models.message.ChatMessage
import com.spydevs.fiestonvirtual.domain.models.message.ChatMessageViewType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject

class SocketIOViewModel(
    private val socket: Socket,
    private val usersRepository: UsersRepository
) : ViewModel() {

    private var isUserConnected = false

    private val _socketIOResult = MutableLiveData<SocketIOResult>()
    val socketIOResult: LiveData<SocketIOResult>
        get() = _socketIOResult

    fun startListeners() {
        socket.on(Socket.EVENT_CONNECT_ERROR, onConnectError)
        socket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectTimeout)
        socket.on(Socket.EVENT_CONNECT, onConnect)
        socket.on(Socket.EVENT_DISCONNECT, onDisconnect)
        socket.on("message", onUpdateChat)
        if (!socket.connected()) {
            socket.connect()
        }
    }

    fun stopListeners() {
        socket.disconnect()
        socket.off(Socket.EVENT_CONNECT, onConnect)
        socket.off(Socket.EVENT_DISCONNECT, onDisconnect)
    }

    private var onConnect = Emitter.Listener {
        viewModelScope.launch(Dispatchers.Main) {
            if (!isUserConnected) {

                val eventId = usersRepository.getLocalUser().idEvent
                val userId = usersRepository.getLocalUser().id

                socket.emit(
                    "join",
                    eventId,
                    userId
                )
                isUserConnected = true
            }
            _socketIOResult.postValue(SocketIOResult.Connection.Success(true))
        }
    }

    private val onConnectTimeout = Emitter.Listener {
        _socketIOResult.postValue(SocketIOResult.Connection.Timeout(true))
    }

    private val onConnectError = Emitter.Listener {
        _socketIOResult.postValue(SocketIOResult.Connection.Error(true))
    }

    private val onDisconnect = Emitter.Listener {
        isUserConnected = false
        _socketIOResult.postValue(SocketIOResult.Connection.Disconnect(true))
    }

    private val onUpdateChat = Emitter.Listener { args ->
        val data = args[0] as JSONObject
        val messageText: String
        val userMessage: String
        val idUserMessage: Int
        val userImage: String
        try {
            messageText = data.getString("messageText")
            userMessage = data.getString("userMessage")
            idUserMessage = data.getInt("idUserMessage")
            userImage = data.getString("userImage")
        } catch (e: JSONException) {
            throw e
        }

        val chatMessage =
            ChatMessage(
                messageText = messageText,
                userName = userMessage,
                userId = idUserMessage,
                userImage = userImage,
                viewType = ChatMessageViewType.INCOMING
            )
        _socketIOResult.postValue(SocketIOResult.ReceiveIncomingMessage.Success(chatMessage))
    }

    fun sendMessage(message: String) {
        viewModelScope.launch(Dispatchers.Main) {
            val userId = usersRepository.getLocalUser().id
            socket.emit("message", userId, message)
        }
    }
}