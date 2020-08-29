package com.spydevs.fiestonvirtual.domain.models.message

data class ChatMessage(
    val messageText: String,
    val userName: String,
    val userId: Int,
    val userImage: String,
    var viewType: ChatMessageViewType
)