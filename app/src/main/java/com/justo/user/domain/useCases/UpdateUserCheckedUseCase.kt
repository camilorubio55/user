package com.justo.user.domain.useCases

import com.justo.user.data.repository.IUserRepository
import javax.inject.Inject

interface IUpdateUserCheckedUseCase {
    suspend fun invoke(id: Int, isChecked : Boolean)
}

class UpdateUserCheckedUseCase @Inject constructor(
    private val repository: IUserRepository
) : IUpdateUserCheckedUseCase {

    override suspend fun invoke(id: Int, isChecked: Boolean) {
        repository.updateUserChecked(id = id, isChecked = isChecked)
    }

}