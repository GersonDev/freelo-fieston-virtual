package com.spydevs.fiestonvirtual.ui.main.chat

import com.spydevs.fiestonvirtual.ui.main.chat.modelll.ChatMessageViewType

data class ChatMessage(
    val messageText: String,
    val viewType: ChatMessageViewType
)