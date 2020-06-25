package com.spydevs.fiestonvirtual.framework.mapper.frominitial

import com.spydevs.fiestonvirtual.data.entities.response.WelcomeResponseEntity
import com.spydevs.fiestonvirtual.domain.models.welcome.Welcome
import com.spydevs.fiestonvirtual.framework.mapper.Mapper

object WelcomeMapper : Mapper<WelcomeResponseEntity, Welcome>() {
    override fun convertFromInitial(i: WelcomeResponseEntity?): Welcome {
        return i?.data?.event.let {
            Welcome(it?.eventName, it?.eventWelcomeText, it?.eventPrize, it?.eventImagePrize)
        }
    }
}