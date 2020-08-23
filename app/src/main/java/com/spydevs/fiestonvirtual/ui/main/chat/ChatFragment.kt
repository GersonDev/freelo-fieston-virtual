package com.spydevs.fiestonvirtual.ui.main.chat

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.ui.main.chat.modelll.ChatMessageViewType
import kotlinx.android.synthetic.main.fragment_chat.*
import org.json.JSONException
import org.json.JSONObject
import org.koin.android.ext.android.inject

class ChatFragment : Fragment(R.layout.fragment_chat) {

    private val mSocket: Socket by inject()

    private var isUserConnected = false
    private lateinit var chatViewModel: ChatViewModel

    private val chatMessagesAdapter: ChatMessagesAdapter by lazy {
        ChatMessagesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError)
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectTimeout)
        mSocket.on(Socket.EVENT_CONNECT, onConnect)
        mSocket.on(Socket.EVENT_DISCONNECT, onDisconnect)

        mSocket.on("updateChat", onUpdateChat)

        if (!mSocket.connected()) {
            mSocket.connect()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
        setupViewListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        mSocket.disconnect()
        mSocket.off(Socket.EVENT_CONNECT, onConnect)
        mSocket.off(Socket.EVENT_DISCONNECT, onDisconnect)
    }

    private fun setupViews() {
        chatFragment_rv_chat.apply {
            adapter = this@ChatFragment.chatMessagesAdapter
        }
    }

    private fun setupViewListener() {
        chatFragment_ib_send.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {
        val message = chatFragment_et_comment.text.toString().trim()
        if (message.isEmpty()) {
            return
        }

        chatFragment_et_comment.setText("")
        val outgoingMessage = ChatMessage(message, ChatMessageViewType.OUTGOING)
        chatMessagesAdapter.addData(outgoingMessage)

        mSocket.emit("newMessage", 5, "Hola buenos días")
    }

    private var onConnect = Emitter.Listener {
        activity?.runOnUiThread(
            Runnable {
                Toast.makeText(activity, "onConnect", Toast.LENGTH_SHORT).show()

                if (!isUserConnected) {
                    mSocket.emit(
                        "join",
                        3,
                        5
                    )
                    isUserConnected = true
                }
            }
        )
    }

    private val onConnectTimeout = Emitter.Listener {
        activity?.runOnUiThread(
            Runnable {
                Toast.makeText(activity, "onConnectTimeout", Toast.LENGTH_SHORT).show()
            })
    }

    private val onConnectError = Emitter.Listener {
        activity?.runOnUiThread(
            Runnable {
                Toast.makeText(activity, "onConnectError", Toast.LENGTH_SHORT).show()
            })
    }

    private val onDisconnect = Emitter.Listener {
        activity?.runOnUiThread(
            Runnable {
                Toast.makeText(activity, "onDisconnect", Toast.LENGTH_SHORT).show()
                isUserConnected = false
            })
    }

    private val onUpdateChat = Emitter.Listener { args ->
        activity?.runOnUiThread(Runnable {
            val data = args[0] as JSONObject
            val username: String
            val message: String
            try {
                username = data.getString("userName")
                message = data.getString("message")
            } catch (e: JSONException) {
                return@Runnable
            }
            // add the message to view
            val chatMessage = ChatMessage(message, ChatMessageViewType.INCOMING)
            chatMessagesAdapter.addData(chatMessage)
        })
    }
}
