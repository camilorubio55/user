package com.justo.user.domain.useCases

import com.justo.user.data.repository.IUserRepository
import javax.inject.Inject

interface IUpdateListUsersUseCase {
    suspend fun invoke()
}

class UpdateListUsersUseCase @Inject constructor(
    private val repository: IUserRepository
) : IUpdateListUsersUseCase {

    override suspend fun invoke() {
        repository.updateListUsersUseCase()
    }

}