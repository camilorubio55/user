package com.justo.user.domain.useCases

import com.justo.user.data.repository.IUserRepository
import javax.inject.Inject

interface IUpdateListUserUseCase {
    suspend fun invoke()
}

class UpdateUserListUseCase @Inject constructor(
    private val repository: IUserRepository
) : IUpdateListUserUseCase {

    override suspend fun invoke() {
        repository.updateListUser()
    }

}