package com.spydevs.fiestonvirtual.data.entities.response

data class MessagesResponseEntity(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val messages: List<Message>
    ) {
        data class Message(
            val idMessage: Int,
            val idUserMessage: Int,
            val messageRegistrationDate: String,
            val messageText: String,
            val userImage: String,
            val userMessage: String
        )
    }
}