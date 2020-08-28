package com.spydevs.fiestonvirtual.domain.usecases.abstractions.message

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.message.ChatMessage
import com.spydevs.fiestonvirtual.domain.resource.ResultType

interface GetChatMessagesUseCase {
    suspend operator fun invoke(): ResultType<List<ChatMessage>, ErrorResponse>
}