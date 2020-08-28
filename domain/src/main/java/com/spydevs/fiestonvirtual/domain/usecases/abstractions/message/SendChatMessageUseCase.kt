package com.spydevs.fiestonvirtual.domain.usecases.abstractions.message

import com.spydevs.fiestonvirtual.domain.models.message.ChatMessage

interface SendChatMessageUseCase {
    suspend operator fun invoke(chatMessage: String): ChatMessage
}