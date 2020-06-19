package com.spydevs.fiestonvirtual.data.repository

import com.spydevs.fiestonvirtual.data.datasource.UsersDataSource
import com.spydevs.fiestonvirtual.domain.models.User
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository

class UsersRepositoryImpl(private val usersDataSource: UsersDataSource) : UsersRepository {
    override suspend fun getUser(): List<User> {
        return usersDataSource.getUsers()
    }

    override suspend fun insertUser(user: User) {
        usersDataSource.insertUser(user)
    }
}