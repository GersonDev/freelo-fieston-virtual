package com.spydevs.fiestonvirtual.data.entities.response

data class WelcomeResponseEntity(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val event: Event
    ) {
        data class Event(
            val eventImagePrize: String,
            val eventLogo: String,
            val eventName: String,
            val eventPrize: String,
            val eventStartDate: String,
            val eventStatus: Int,
            val eventWelcomeText: String,
            val idEvent: Int
        )
    }
}