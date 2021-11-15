package com.justo.user.domain.useCases

import com.justo.user.data.repository.IUserRepository
import com.justo.user.domain.models.User
import com.justo.user.utility.Result
import javax.inject.Inject

interface IGetUserUseCase {
    suspend fun invoke() : List<User>?
}

class GetUserUseCase @Inject constructor(
    private val repository: IUserRepository
) : IGetUserUseCase {

    override suspend fun invoke(): List<User>? {
        return when (val result = repository.getUser()) {
            is Result.Success -> result.data
            is Result.Error -> null
        }
    }

}