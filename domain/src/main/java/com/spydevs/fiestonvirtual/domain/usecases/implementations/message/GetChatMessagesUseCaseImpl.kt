package com.spydevs.fiestonvirtual.domain.usecases.implementations.message

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.message.ChatMessage
import com.spydevs.fiestonvirtual.domain.models.message.ChatMessageViewType
import com.spydevs.fiestonvirtual.domain.models.message.MessageRequest
import com.spydevs.fiestonvirtual.domain.repository.ChatMessageRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.message.GetChatMessagesUseCase

class GetChatMessagesUseCaseImpl(
    private val chatMessageRepository: ChatMessageRepository,
    private val usersRepository: UsersRepository
) : GetChatMessagesUseCase {
    override suspend fun invoke(): ResultType<List<ChatMessage>, ErrorResponse> {
        val localUser = usersRepository.getLocalUser()
        val messageRequest = MessageRequest(localUser.id, localUser.idEvent)
        return when (val result = chatMessageRepository.getRemoteMessages(messageRequest)) {
            is ResultType.Success -> {
                result.value.forEach {
                    if(it.userId == localUser.id) {
                        it.viewType = ChatMessageViewType.OUTGOING
                    } else {
                        it.viewType = ChatMessageViewType.INCOMING
                    }
                }
                result
            }
            is ResultType.Error -> {
                result
            }
        }
    }
}