package com.spydevs.fiestonvirtual.framework.socketio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.ui.main.chat.ChatMessage
import com.spydevs.fiestonvirtual.ui.main.chat.ChatMessageViewType
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
                socket.emit(
                    "join",
                    usersRepository.getLocalUser().idEvent,
                    usersRepository.getLocalUser().id
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
        val username: String
        val message: String
        try {
            username = data.getString("userName")
            message = data.getString("message")
        } catch (e: JSONException) {
            throw e
        }

        val chatMessage = ChatMessage(
            message,
            ChatMessageViewType.INCOMING
        )
        _socketIOResult.postValue(SocketIOResult.ReceiveIncomingMessage.Success(chatMessage))
    }

    fun sendMessage(message: String) {
        viewModelScope.launch(Dispatchers.Main) {
            socket.emit("message", 5, message)
        }
    }
}