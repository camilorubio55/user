package com.justo.user.domain.useCases

import com.justo.user.data.repository.IUserRepository
import javax.inject.Inject

interface IGetUserUseCase {
    suspend fun invoke() : String
}

class GetUserUseCase @Inject constructor(
    private val repository: IUserRepository
) : IGetUserUseCase {

    override suspend fun invoke(): String {
        return repository.getUser()
    }

}