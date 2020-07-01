package com.spydevs.fiestonvirtual.data.datasource

import com.spydevs.fiestonvirtual.domain.models.user.GetRemoteUserRequest
import com.spydevs.fiestonvirtual.domain.models.user.User
import com.spydevs.fiestonvirtual.domain.resource.ResultType

interface UsersDataSource {

    suspend fun getLocalUser(): User

    suspend fun setLoggedInUser(user: User)

    suspend fun getRemoteUser(getRemoteUserRequest: GetRemoteUserRequest): ResultType<User, String>

}