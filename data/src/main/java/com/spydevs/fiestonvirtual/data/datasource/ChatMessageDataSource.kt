package com.spydevs.fiestonvirtual.data.datasource

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.message.ChatMessage
import com.spydevs.fiestonvirtual.domain.models.message.MessageRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType

interface ChatMessageDataSource {

    /**
     * @param [messageRequest] This object is necessary for return the messages.
     * @return all the chat messages from the web services.
     */
    suspend fun getRemoteMessages(
        messageRequest: MessageRequest
    ): ResultType<List<ChatMessage>, ErrorResponse>
}