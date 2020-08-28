package com.spydevs.fiestonvirtual.framework.mapper.implementations

import com.spydevs.fiestonvirtual.data.entities.response.MessagesResponseEntity
import com.spydevs.fiestonvirtual.domain.models.message.ChatMessage
import com.spydevs.fiestonvirtual.domain.models.message.ChatMessageViewType

fun MessagesResponseEntity.toMessages(): List<ChatMessage> {
    return this.data.messages.map {
        ChatMessage(
            messageText = it.messageText,
            userName = it.userMessage,
            userId = it.idUserMessage,
            userImage = it.userImage,
            viewType = ChatMessageViewType.INCOMING
        )
    }
}