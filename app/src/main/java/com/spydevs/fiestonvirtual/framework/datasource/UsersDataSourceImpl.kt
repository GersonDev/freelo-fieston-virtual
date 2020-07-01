package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.UsersDataSource
import com.spydevs.fiestonvirtual.domain.models.user.GetRemoteUserRequest
import com.spydevs.fiestonvirtual.domain.models.user.User
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.framework.api.FiestonVirtualApi
import com.spydevs.fiestonvirtual.framework.api.NetworkResponse
import com.spydevs.fiestonvirtual.framework.database.dao.UsersDao
import com.spydevs.fiestonvirtual.framework.mapper.implementations.UserEntityMapper
import com.spydevs.fiestonvirtual.framework.mapper.implementations.UserMapper
import com.spydevs.fiestonvirtual.framework.mapper.implementations.UserResponseEntityMapper

class UsersDataSourceImpl(
    private val fiestonVirtualApi: FiestonVirtualApi,
    private val usersDao: UsersDao
) : UsersDataSource {

    override suspend fun getLocalUser(): User {
        return UserEntityMapper.convertFromInitial(usersDao.getUsers()[0])
    }

    override suspend fun setLoggedInUser(user: User) {
        usersDao.setLoggedInUser(UserMapper.convertFromInitial(user))
    }

    override suspend fun getRemoteUser(getRemoteUserRequest: GetRemoteUserRequest): ResultType<User, String> {
        return when (val result = fiestonVirtualApi.getDataUser(getRemoteUserRequest)) {
            is NetworkResponse.Success -> {
                ResultType.Success(
                    UserResponseEntityMapper.convertFromInitial(
                        result.body.data
                    )
                )
            }
            is NetworkResponse.ApiError -> {
                ResultType.Error(result.body.message)
            }
            is NetworkResponse.NetworkError -> {
                ResultType.Error(result.error.message ?: "")
            }
            is NetworkResponse.UnknownError -> {
                ResultType.Error(result.error.message ?: "")
            }

        }
    }


}