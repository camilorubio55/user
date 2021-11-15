package com.justo.user.domain.useCases

import com.justo.user.data.repository.IUserRepository
import javax.inject.Inject

interface IUpdateUserSelectionStatusUseCase {
    suspend fun invoke(status : Boolean)
}

class UpdateUserSelectionStatusUseCase @Inject constructor(
    private val repository: IUserRepository
) : IUpdateUserSelectionStatusUseCase {

    override suspend fun invoke(status : Boolean) {
        repository.updateUserSelectionStatus(status = status)
    }

}