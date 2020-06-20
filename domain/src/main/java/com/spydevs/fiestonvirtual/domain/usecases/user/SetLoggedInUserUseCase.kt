package com.spydevs.fiestonvirtual.domain.usecases.user

import com.spydevs.fiestonvirtual.domain.models.User

interface SetLoggedInUserUseCase {
    /**
     * This method set logged in user to application.
     */
    suspend fun setLoggedInUser(user: User)

}