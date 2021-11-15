package com.justo.user.data.repository

import com.justo.user.data.dataSource.IUserLocalDataSource
import com.justo.user.data.dataSource.IUserRemoteDataSource
import com.justo.user.domain.models.User
import com.justo.user.utility.Constants.TypeErrors.DEFAULT_ERROR
import com.justo.user.utility.Result
import javax.inject.Inject

interface IUserRepository {
    suspend fun getUser() : Result<List<User>>
}

class UserRepository @Inject constructor(
    private val userLocalDataSource: IUserLocalDataSource,
    private val userRemoteDataSource: IUserRemoteDataSource
) : IUserRepository {

    override suspend fun getUser() : Result<List<User>> {
        return when (val result = userRemoteDataSource.getUser()) {
            is Result.Success -> Result.Success(result.data)
            is Result.Error -> Result.Error(result.exception)
        }
    }
}