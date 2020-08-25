package com.spydevs.fiestonvirtual.ui.main.chat

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.framework.socketio.SocketIOResult
import com.spydevs.fiestonvirtual.framework.socketio.SocketIOViewModel
import kotlinx.android.synthetic.main.fragment_chat.*
import org.koin.android.ext.android.inject

class ChatFragment : Fragment(R.layout.fragment_chat) {

    private val chatViewModel: ChatViewModel by inject()
    private val socketIOViewModel: SocketIOViewModel by inject()

    private val chatMessagesAdapter: ChatMessagesAdapter by lazy {
        ChatMessagesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        socketIOViewModel.startListeners()
        subscribeToChatViewModel()
        subscribeToSocketIOViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
        setupViewListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        socketIOViewModel.stopListeners()
    }

    private fun setupViews() {
        chatFragment_rv_chat.apply {
            adapter = this@ChatFragment.chatMessagesAdapter
        }
    }

    private fun setupViewListener() {
        chatFragment_ib_send.setOnClickListener {
            chatViewModel.sendMessage(chatFragment_et_comment.text.toString())
        }
    }

    private fun subscribeToChatViewModel() {
        chatViewModel.chatResult.observe(this, Observer {
            when(it) {
                is ChatResult.SendOutgoingMessage.Success -> {
                    chatFragment_et_comment.text?.clear()
                    chatMessagesAdapter.addData(it.chatMessage)
                    socketIOViewModel.sendMessage(it.chatMessage.messageText)
                }
                is ChatResult.SendOutgoingMessage.Error -> {
                    Toast.makeText(activity, it.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun subscribeToSocketIOViewModel() {
        socketIOViewModel.socketIOResult.observe(this, Observer {
            when(it) {
                is SocketIOResult.ReceiveIncomingMessage.Success -> {
                    chatMessagesAdapter.addData(it.chatMessage)
                }
            }
        })
    }

//    private var onConnect = Emitter.Listener {
//        activity?.runOnUiThread(
//            Runnable {
//                Toast.makeText(activity, "onConnect", Toast.LENGTH_SHORT).show()
//
//                if (!isUserConnected) {
//                    mSocket.emit(
//                        "join",
//                        3,
//                        5
//                    )
//                    isUserConnected = true
//                }
//            }
//        )
//    }
//
//    private val onConnectTimeout = Emitter.Listener {
//        activity?.runOnUiThread(
//            Runnable {
//                Toast.makeText(activity, "onConnectTimeout", Toast.LENGTH_SHORT).show()
//            })
//    }
//
//    private val onConnectError = Emitter.Listener {
//        activity?.runOnUiThread(
//            Runnable {
//                Log.e("veamos", "carlos $it")
//                Toast.makeText(activity, "onConnectError", Toast.LENGTH_SHORT).show()
//            })
//    }
//
//    private val onDisconnect = Emitter.Listener {
//        activity?.runOnUiThread(
//            Runnable {
//                Toast.makeText(activity, "onDisconnect", Toast.LENGTH_SHORT).show()
//                isUserConnected = false
//            })
//    }
//
//    private val onUpdateChat = Emitter.Listener { args ->
//        activity?.runOnUiThread(Runnable {
//            val data = args[0] as JSONObject
//            val username: String
//            val message: String
//            try {
//                username = data.getString("userName")
//                message = data.getString("message")
//            } catch (e: JSONException) {
//                return@Runnable
//            }
//            // add the message to view
//            val chatMessage = ChatMessage(message, ChatMessageViewType.INCOMING)
//            chatMessagesAdapter.addData(chatMessage)
//        })
//    }
}
