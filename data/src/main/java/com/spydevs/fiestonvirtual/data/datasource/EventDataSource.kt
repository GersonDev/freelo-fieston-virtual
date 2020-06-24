package com.spydevs.fiestonvirtual.data.datasource

import com.spydevs.fiestonvirtual.domain.models.welcome.Welcome
import com.spydevs.fiestonvirtual.domain.resource.ResultType

interface EventDataSource {
    suspend fun getWelcome(eventId: Int, userId: Int): ResultType<Welcome, String>
}