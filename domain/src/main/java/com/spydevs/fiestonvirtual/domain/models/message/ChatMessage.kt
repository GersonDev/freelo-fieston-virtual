package com.spydevs.fiestonvirtual.domain.models.message

import com.spydevs.fiestonvirtual.domain.models.message.ChatMessageViewType

data class ChatMessage(
    val messageText: String,
    val userName: String,
    val userId: Int,
    val userImage: String,
    val viewType: ChatMessageViewType
)