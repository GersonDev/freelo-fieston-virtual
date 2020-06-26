package com.spydevs.fiestonvirtual.domain.usecases.abstractions.user

import com.spydevs.fiestonvirtual.domain.models.user.User

/**
 * this use case return the user logged in the application.
 */
interface GetLocalUserUseCase {
    suspend operator fun invoke(): User
}