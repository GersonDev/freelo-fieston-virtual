package com.spydevs.fiestonvirtual.domain.usecases.implementations.message

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.message.ChatMessage
import com.spydevs.fiestonvirtual.domain.models.message.MessageRequest
import com.spydevs.fiestonvirtual.domain.repository.ChatMessageRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.message.GetChatMessagesUseCase

class GetChatMessagesUseCaseImpl(
    private val chatMessageRepository: ChatMessageRepository,
    private val usersRepository: UsersRepository
): GetChatMessagesUseCase {
    override suspend fun invoke(): ResultType<List<ChatMessage>, ErrorResponse> {
        val messageRequest = MessageRequest(usersRepository.getLocalUser().id, usersRepository.getLocalUser().idEvent)
        return chatMessageRepository.getRemoteMessages(messageRequest)
    }
}