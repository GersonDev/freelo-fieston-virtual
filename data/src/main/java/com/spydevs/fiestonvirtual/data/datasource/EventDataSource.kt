package com.spydevs.fiestonvirtual.data.datasource

import com.spydevs.fiestonvirtual.domain.models.welcome.Welcome
import com.spydevs.fiestonvirtual.domain.models.welcome.WelcomeRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * Data Source for handling information coming from direct framework layer
 * either from web services or local db or cache, etc
 */
interface EventDataSource {
    /**
     * Get welcome information from whatever adapter in the framework layer
     * @param [eventId] the event id
     * @param [userId] the user id
     * @return a [ResultType] that contains information encapsulated in a domain model
     */
    suspend fun getWelcome(welcomeRequest: WelcomeRequest): ResultType<Welcome, String>
}