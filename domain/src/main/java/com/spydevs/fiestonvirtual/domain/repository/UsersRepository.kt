package com.spydevs.fiestonvirtual.domain.repository

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.user.GetRemoteUserRequest
import com.spydevs.fiestonvirtual.domain.models.user.SignOutRequest
import com.spydevs.fiestonvirtual.domain.models.user.User
import com.spydevs.fiestonvirtual.domain.resource.ResultType

/**
 * Abstraction for getting all information about user
 * either by web services, database, cache or whatever other sources
 */
interface UsersRepository {
    suspend fun getLocalUser(): User
    suspend fun setLoggedInUser(user: User)
    suspend fun getRemoteUser(
        getRemoteUserRequest: GetRemoteUserRequest
    ): ResultType<User, ErrorResponse>

    suspend fun updateLocalTotalScoreOfUser(
        idUser: Int,
        totalScore: Int
    )

    suspend fun signOut(
        signOutRequest: SignOutRequest
    ): ResultType<Boolean, ErrorResponse>

    suspend fun deleteLocalAllUsers()
}