package com.justo.user.data.dataSource

import com.justo.user.data.db.dao.UserDao
import com.justo.user.data.mapper.IMapper
import com.justo.user.utility.Result
import javax.inject.Inject

interface IUserLocalDataSource {
    suspend fun getUser() : Result<String>
}

class UserLocalDataSource @Inject constructor(
    private val mapper: IMapper,
    private val userDao: UserDao
) : IUserLocalDataSource {

    override suspend fun getUser(): Result<String> {
        return Result.Success(userDao.getUsers()[0].email)
    }

}