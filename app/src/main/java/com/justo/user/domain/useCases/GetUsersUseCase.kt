package com.justo.user.domain.useCases

import androidx.lifecycle.LiveData
import com.justo.user.data.repository.IUserRepository
import com.justo.user.domain.models.User
import javax.inject.Inject

interface IGetUsersUseCase {
    fun invoke() : LiveData<List<User>?>
}

class GetUsersUseCase @Inject constructor(
    private val repository: IUserRepository
) : IGetUsersUseCase {

    override fun invoke(): LiveData<List<User>?> {
        return repository.getUsers()
    }

}