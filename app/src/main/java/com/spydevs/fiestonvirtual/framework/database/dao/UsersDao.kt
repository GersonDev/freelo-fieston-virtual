package com.spydevs.fiestonvirtual.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Transaction
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

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    @Transaction
    suspend fun setLoggedInUser(loggedInUser: UserEntity) {
        deleteAllUsers()
        insertUser(loggedInUser)
    }

    @Query("UPDATE users SET total_score=:totalScore WHERE id=:idUser")
    suspend fun updateTotalScoreOfUser(
        idUser: Int,
        totalScore: Int
    )

    @Query("UPDATE users SET avatar=:avatar WHERE id=:idUser")
    suspend fun updateAvatar(
        idUser: Int,
        avatar: String
    )

    @Query("UPDATE users SET token=:token WHERE id=:idUser")
    suspend fun updateLocalToken(idUser: Int, token: String)

}