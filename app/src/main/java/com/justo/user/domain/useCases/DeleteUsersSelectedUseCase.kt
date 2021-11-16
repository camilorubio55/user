package com.justo.user.domain.useCases

import com.justo.user.data.repository.IUserRepository
import javax.inject.Inject


interface IDeleteUsersSelectedUseCase {
    suspend fun invoke()
}

class DeleteUsersSelectedUseCase @Inject constructor(
    private val repository: IUserRepository
) : IDeleteUsersSelectedUseCase {

    override suspend fun invoke() {
        repository.deleteUsersSelected()
    }

}