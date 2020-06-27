package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.EventDataSource
import com.spydevs.fiestonvirtual.data.entities.response.WelcomeResponseEntity
import com.spydevs.fiestonvirtual.domain.models.welcome.Welcome
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.framework.api.FiestonVirtualApi
import com.spydevs.fiestonvirtual.framework.mapper.implementations.WelcomeMapper

class EventDataSourceImpl(private val fiestonVirtualApi: FiestonVirtualApi) : EventDataSource {
    override suspend fun getWelcome(eventId: Int, userId: Int): ResultType<Welcome, String> {

        //TODO IMPLEMENT WITH REAL WEB SERVICE

        val welcomeResponseEntity = WelcomeResponseEntity(
            WelcomeResponseEntity.Data(
                WelcomeResponseEntity.Data.Event(
                    eventHost = "Omar Ramirez",
                    eventImagePrize = "https://i.pinimg.com/474x/81/e5/7b/81e57b518630e7f7fbd5e651be5c0980--tomato-vegetable-tomato-plants.jpg",
                    eventLogo = "https://fiestonvirtual.com/images/eventos/1/logo/logo-fieston-01.jpg",
                    eventName = "Fiestón Virtual",
                    eventPrize = "Un viaje para 2 personas a Cancún",
                    eventStartDate = "15/07/2020 18:00:00",
                    eventStatus = 1,
                    eventWelcomeText = "Hoy celebramos el aniversario 50 de Super Mercados Metro.",
                    idEvent = 1
                )
            ), "Respuesta exitosa"
        )
        val mappedWelcome = WelcomeMapper.convertFromInitial(welcomeResponseEntity)
        return ResultType.Success(mappedWelcome)
    }
}