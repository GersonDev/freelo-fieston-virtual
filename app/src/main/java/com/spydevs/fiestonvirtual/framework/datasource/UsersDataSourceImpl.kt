package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.UsersDataSource
import com.spydevs.fiestonvirtual.domain.models.User
import com.spydevs.fiestonvirtual.framework.database.dao.UsersDao
import com.spydevs.fiestonvirtual.framework.mapper.frominitial.UserMapper
import com.spydevs.fiestonvirtual.framework.mapper.frominitial.UsersMapper

class UsersDataSourceImpl(
    private val usersDao: UsersDao
) : UsersDataSource {
    override suspend fun getUsers(): List<User> {
        return UsersMapper.convertFromInitial(usersDao.getUsers())
    }

    //TODO refactor when service is correct.
    override suspend fun setLoggedInUser(user: User) {
        usersDao.setLoggedInUser(UserMapper.convertFromInitial(user))
    }
}