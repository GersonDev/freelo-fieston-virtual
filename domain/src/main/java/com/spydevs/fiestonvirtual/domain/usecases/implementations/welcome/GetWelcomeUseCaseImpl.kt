package com.spydevs.fiestonvirtual.domain.usecases.implementations.welcome

import com.spydevs.fiestonvirtual.domain.models.welcome.Welcome
import com.spydevs.fiestonvirtual.domain.repository.EventRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.welcome.GetWelcomeUseCase

class GetWelcomeUseCaseImpl(private val eventRepository: EventRepository) : GetWelcomeUseCase {
    override suspend fun invoke(): ResultType<Welcome, String> {
        //TODO GET USER ID AND EVENT ID FROM PERSISTENCE LAYER
        return eventRepository.getWelcome(1, 1)
    }
}