package com.justo.user.data.repository

import com.justo.user.data.dataSource.IUserRemoteDataSource
import com.justo.user.utility.Constants.TypeErrors.DEFAULT_ERROR
import com.justo.user.utility.Result
import javax.inject.Inject

interface IUserRepository {
    suspend fun getUser() : String
}

class UserRepository @Inject constructor(
    private val userRemoteDataSource: IUserRemoteDataSource
) : IUserRepository {

    override suspend fun getUser(): String {
        return when (val result = userRemoteDataSource.getUser()) {
            is Result.Success -> result.data
            is Result.Error -> result.exception.localizedMessage ?: DEFAULT_ERROR
        }
    }
}