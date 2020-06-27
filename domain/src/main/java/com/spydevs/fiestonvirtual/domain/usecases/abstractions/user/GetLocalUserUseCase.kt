package com.spydevs.fiestonvirtual.domain.usecases.abstractions.user

import com.spydevs.fiestonvirtual.domain.models.user.User
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * Use case for get local information about the user.
 */
interface GetLocalUserUseCase {

    /**
     * @return [User] directly instead of a [ResultType] because of the response came from DB
     */
    suspend operator fun invoke(): User
}