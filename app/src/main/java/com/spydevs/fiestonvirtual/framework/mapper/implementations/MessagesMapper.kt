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
            userImage = "http://fiestonvirtual.com/app/images/users/5/tn_7fb06461-44ea-4150-ac06-de1c637b8ac2.jpg",
            viewType = ChatMessageViewType.INCOMING
        )
    }
}