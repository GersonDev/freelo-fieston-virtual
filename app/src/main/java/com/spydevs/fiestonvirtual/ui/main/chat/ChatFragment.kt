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
        subscribeToChatViewModel()
        subscribeToSocketIOViewModel()
        socketIOViewModel.startListeners()
        chatViewModel.getMessages()
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

    private fun scrollToBottom() {
        chatFragment_rv_chat.scrollToPosition(chatMessagesAdapter.itemCount - 1)
    }

    private fun subscribeToChatViewModel() {
        chatViewModel.chatResult.observe(this, Observer {
            when(it) {
                is ChatResult.GetMessages.Success -> {
                    chatMessagesAdapter.addAllData(it.messages)
                    scrollToBottom()
                }
                is ChatResult.SendOutgoingMessage.Success -> {
                    chatFragment_et_comment.text?.clear()
                    chatMessagesAdapter.addData(it.chatMessage)
                    scrollToBottom()
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
                is SocketIOResult.Connection.Success -> {
                    Toast.makeText(activity, CONNECTED, Toast.LENGTH_SHORT).show()
                }
                is SocketIOResult.Connection.Error -> {
                    Toast.makeText(activity, ERROR, Toast.LENGTH_SHORT).show()
                }
                is SocketIOResult.Connection.Disconnect -> {
                    Toast.makeText(activity, DISCONNECT, Toast.LENGTH_SHORT).show()
                }
                is SocketIOResult.ReceiveIncomingMessage.Success -> {
                    chatMessagesAdapter.addData(it.chatMessage)
                    scrollToBottom()
                }
            }
        })
    }

    companion object {
        const val CONNECTED = "Connected"
        const val ERROR = "Error"
        const val DISCONNECT = "Disconnect"
    }

}
