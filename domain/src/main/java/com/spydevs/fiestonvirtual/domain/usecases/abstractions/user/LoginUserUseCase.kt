package com.spydevs.fiestonvirtual.domain.usecases.abstractions.user

import com.spydevs.fiestonvirtual.domain.models.code.ValidateCodeRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * This use case set logged user to the application.
 */
interface LoginUserUseCase {

    /**
     * @param [validateCodeRequest] This contains the the data of the logged user.
     */
    suspend operator fun invoke(
        validateCodeRequest: ValidateCodeRequest
    ): ResultType<Boolean, String>

}