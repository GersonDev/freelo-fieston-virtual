package com.spydevs.fiestonvirtual.domain.repository

import com.spydevs.fiestonvirtual.domain.models.User

/**
 * Abstraction for getting all information about user
 * either by web services, database, cache or whatever other sources
 */
interface UsersRepository {
    suspend fun getUser(): List<User>
    suspend fun insertUser(user: User)
}