package com.spydevs.fiestonvirtual.domain.usecases.abstractions.user

import com.spydevs.fiestonvirtual.domain.models.User

/**
 * This use case set logged user to the application.
 */
interface SetLoggedInUserUseCase {

    /**
     * @param [user] contains the data of the logged user.
     */
    suspend fun invoke(user: User)

}