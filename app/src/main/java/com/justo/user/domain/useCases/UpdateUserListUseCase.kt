package com.justo.user.domain.useCases

import com.justo.user.data.repository.IUserRepository
import javax.inject.Inject

interface IUpdateUserListUseCase {
    suspend fun invoke()
}

class UpdateUserListUseCase @Inject constructor(
    private val repository: IUserRepository
) : IUpdateUserListUseCase {

    override suspend fun invoke() {
        repository.updateListUser()
    }

}