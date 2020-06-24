package com.spydevs.fiestonvirtual.domain.usecases.abstractions.welcome

import com.spydevs.fiestonvirtual.domain.models.welcome.Welcome
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * Use case to get data to welcome the user.
 */
interface GetWelcomeUseCase {

    /**
     * Invoke the action of uploading the image to the server.
     * @return a model from the domain layer
     */
    suspend operator fun invoke(): ResultType<Welcome, String>
}