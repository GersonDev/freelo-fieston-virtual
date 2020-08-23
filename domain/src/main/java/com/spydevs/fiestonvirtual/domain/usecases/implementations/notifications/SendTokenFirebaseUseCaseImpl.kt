package com.spydevs.fiestonvirtual.domain.usecases.implementations.notifications

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.notifications.SendTokenRequest
import com.spydevs.fiestonvirtual.domain.repository.FcmRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.notifications.SendTokenFirebaseUseCase

class SendTokenFirebaseUseCaseImpl(
    private val usersRepository: UsersRepository,
    private val fcmRepository: FcmRepository
) : SendTokenFirebaseUseCase {

    override suspend fun invoke(token: String): ResultType<Boolean, ErrorResponse> {
        return if (usersRepository.getLocalUsers().isNotEmpty()) {
            val user = usersRepository.getLocalUser()
            when (fcmRepository.sendToken(
                SendTokenRequest(
                    idUser = user.id,
                    token = token
                )
            )) {
                is ResultType.Success -> {
                    ResultType.Success(true)

                }
                is ResultType.Error -> {
                    ResultType.Success(false)
                }
            }
        } else {
            ResultType.Success(false)
        }
    }

}