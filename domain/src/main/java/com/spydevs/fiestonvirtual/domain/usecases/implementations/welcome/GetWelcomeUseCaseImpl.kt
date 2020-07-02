package com.spydevs.fiestonvirtual.domain.usecases.implementations.welcome

import com.spydevs.fiestonvirtual.domain.models.welcome.Welcome
import com.spydevs.fiestonvirtual.domain.models.welcome.WelcomeRequest
import com.spydevs.fiestonvirtual.domain.repository.EventRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.welcome.GetWelcomeUseCase

class GetWelcomeUseCaseImpl(
    private val eventRepository: EventRepository,
    private val usersRepository: UsersRepository
) : GetWelcomeUseCase {
    override suspend fun invoke(): ResultType<Welcome, String> {
        val user = usersRepository.getLocalUser()
        val welcomeRequest = WelcomeRequest(user.idEvent)
        return eventRepository.getWelcome(welcomeRequest)
    }
}