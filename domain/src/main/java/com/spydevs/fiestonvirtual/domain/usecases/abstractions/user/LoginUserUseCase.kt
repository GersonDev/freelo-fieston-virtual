package com.spydevs.fiestonvirtual.domain.usecases.abstractions.user

import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This use case set logged user to the application.
 */
interface LoginUserUseCase {

    /**
     * @param [eventCode] contains the data of the logged user.
     */
    suspend operator fun invoke(eventCode: String): ResultType<Boolean, String>

}