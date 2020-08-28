package com.spydevs.fiestonvirtual.domain.repository

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.message.ChatMessage
import com.spydevs.fiestonvirtual.domain.models.message.MessageRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType

interface ChatMessageRepository {
    suspend fun getRemoteMessages(
        messageRequest: MessageRequest
    ): ResultType<List<ChatMessage>, ErrorResponse>
}