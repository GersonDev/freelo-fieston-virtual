package com.spydevs.fiestonvirtual.data.repository

import com.spydevs.fiestonvirtual.data.datasource.ChatMessageDataSource
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.message.ChatMessage
import com.spydevs.fiestonvirtual.domain.models.message.MessageRequest
import com.spydevs.fiestonvirtual.domain.repository.ChatMessageRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType

class ChatMessageRepositoryImpl(
    private val chatMessageDataSource: ChatMessageDataSource
): ChatMessageRepository {
    override suspend fun getRemoteMessages(messageRequest: MessageRequest): ResultType<List<ChatMessage>, ErrorResponse> {
        return chatMessageDataSource.getRemoteMessages(messageRequest)
    }
}