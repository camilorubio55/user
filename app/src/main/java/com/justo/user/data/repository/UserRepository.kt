package com.justo.user.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.justo.user.data.dataSource.IUserLocalDataSource
import com.justo.user.data.dataSource.IUserRemoteDataSource
import com.justo.user.domain.models.User
import com.justo.user.utility.Result
import javax.inject.Inject

interface IUserRepository {
    suspend fun updateListUser()
    suspend fun updateUserSelectionStatus(status : Boolean)
    suspend fun updateUserChecked(id : Int, isChecked : Boolean)
    fun getUsers() : LiveData<List<User>?>
}

class UserRepository @Inject constructor(
    private val userLocalDataSource: IUserLocalDataSource,
    private val userRemoteDataSource: IUserRemoteDataSource
) : IUserRepository {

    override suspend fun updateListUser()  {
        when (val result = userRemoteDataSource.getUsers()) {
            is Result.Success -> userLocalDataSource.saveUsers(result.data)
        }
    }

    override suspend fun updateUserSelectionStatus(status : Boolean) {
        userLocalDataSource.updateUserSelectionStatus(status = status)
    }

    override suspend fun updateUserChecked(id: Int, isChecked: Boolean) {
        userLocalDataSource.updateUserChecked(id = id, isChecked = isChecked)
    }

    override fun getUsers(): LiveData<List<User>?> {
        return userLocalDataSource.getUsers().asLiveData()
    }
}