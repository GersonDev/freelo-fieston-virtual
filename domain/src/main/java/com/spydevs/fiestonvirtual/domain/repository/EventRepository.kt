package com.spydevs.fiestonvirtual.domain.repository

import com.spydevs.fiestonvirtual.domain.models.welcome.Welcome
import com.spydevs.fiestonvirtual.domain.models.welcome.WelcomeRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This repository is used to handle information about the event
 */
interface EventRepository {
    suspend fun getWelcome(welcomeRequest: WelcomeRequest): ResultType<Welcome, String>
}