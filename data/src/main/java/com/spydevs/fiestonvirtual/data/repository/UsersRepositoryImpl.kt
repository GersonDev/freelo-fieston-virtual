package com.spydevs.fiestonvirtual.data.repository

import com.spydevs.fiestonvirtual.data.datasource.UsersDataSource
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.user.GetRemoteUserRequest
import com.spydevs.fiestonvirtual.domain.models.user.User
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType

class UsersRepositoryImpl(
    private val usersDataSource: UsersDataSource
) : UsersRepository {
    override suspend fun getLocalUser(): User {
        return usersDataSource.getLocalUser()
    }

    override suspend fun setLoggedInUser(user: User) {
        usersDataSource.setLoggedInUser(user)
    }

    override suspend fun getRemoteUser(
        getRemoteUserRequest: GetRemoteUserRequest
    ): ResultType<User, ErrorResponse> {
        return usersDataSource.getRemoteUser(getRemoteUserRequest)
    }

}