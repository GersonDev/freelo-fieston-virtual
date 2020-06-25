package com.spydevs.fiestonvirtual.data.repository

import com.spydevs.fiestonvirtual.data.datasource.EventDataSource
import com.spydevs.fiestonvirtual.domain.models.welcome.Welcome
import com.spydevs.fiestonvirtual.domain.repository.EventRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType

class EventRepositoryImpl(private val eventDataSource: EventDataSource) : EventRepository {
    override suspend fun getWelcome(eventId: Int, userId: Int): ResultType<Welcome, String> {
        return eventDataSource.getWelcome(eventId, userId)
    }
}