package com.spydevs.fiestonvirtual.data.datasource

import com.spydevs.fiestonvirtual.domain.models.User

interface UsersDataSource {

    suspend fun getUsers(): List<User>

    suspend fun insertUser(user: User)

}