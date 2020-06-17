package com.spydevs.fiestonvirtual.framework.database.dao

import androidx.room.*
import com.spydevs.fiestonvirtual.framework.database.entities.UserEntity

@Dao
interface UsersDao {

    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<UserEntity>

    @Insert
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Transaction
    open suspend fun setLoggedInUser(loggedInUser: UserEntity) {
        deleteUser(loggedInUser)
        insertUser(loggedInUser)
    }

}