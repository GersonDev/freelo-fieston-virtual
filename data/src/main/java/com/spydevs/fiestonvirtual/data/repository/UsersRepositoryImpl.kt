package com.spydevs.fiestonvirtual.data.repository

import com.spydevs.fiestonvirtual.data.datasource.UsersDataSource
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.user.GetRemoteUserRequest
import com.spydevs.fiestonvirtual.domain.models.user.SignOutRequest
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

    override suspend fun updateLocalTotalScoreOfUser(idUser: Int, totalScore: Int) {
        return usersDataSource.updateTotalScoreOfUser(idUser, totalScore)
    }

    override suspend fun signOut(
        signOutRequest: SignOutRequest
    ): ResultType<Boolean, ErrorResponse> {
        return usersDataSource.signOut(signOutRequest)
    }

    override suspend fun deleteLocalAllUsers() {
        return usersDataSource.deleteLocalAllUsers()
    }

    override suspend fun getLocalUsers(): List<User> {
        return usersDataSource.getLocalUsers()
    }

    override suspend fun updateLocalToken(idUser: Int, token: String) {
        usersDataSource.updateLocalToken(
            idUser,
            token
        )
    }

}