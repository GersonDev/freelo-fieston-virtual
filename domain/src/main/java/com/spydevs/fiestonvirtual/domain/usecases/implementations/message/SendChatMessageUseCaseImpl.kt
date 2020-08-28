package com.spydevs.fiestonvirtual.domain.usecases.implementations.message

import com.spydevs.fiestonvirtual.domain.models.message.ChatMessage
import com.spydevs.fiestonvirtual.domain.models.message.ChatMessageViewType
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.message.SendChatMessageUseCase

class SendChatMessageUseCaseImpl(
    private val usersRepository: UsersRepository
) : SendChatMessageUseCase {
    override suspend operator fun invoke(chatMessage: String): ChatMessage {
        return ChatMessage(
                messageText = chatMessage,
                userName = "${usersRepository.getLocalUser().name} ${usersRepository.getLocalUser().lastName}",
                userId = usersRepository.getLocalUser().id,
                userImage = usersRepository.getLocalUser().avatar,
                viewType = ChatMessageViewType.OUTGOING
            )
    }

}