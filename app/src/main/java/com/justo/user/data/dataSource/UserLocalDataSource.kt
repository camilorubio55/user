package com.justo.user.data.dataSource

import com.justo.user.data.db.dao.UserDao
import com.justo.user.data.mapper.IMapper
import com.justo.user.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface IUserLocalDataSource {
    fun getUsers() : Flow<List<User>>
    suspend fun saveUsers(listUser: List<User>)
    suspend fun updateUserSelectionStatus(status : Boolean)
}

class UserLocalDataSource @Inject constructor(
    private val mapper: IMapper,
    private val userDao: UserDao
) : IUserLocalDataSource {

    override fun getUsers(): Flow<List<User>> {
        return userDao.getUsers().map {
            mapper.mapListUserDBToListUser(listUserDB = it)
        }
    }

    override suspend fun saveUsers(listUser: List<User>) {
        userDao.insertUsers(mapper.mapListUserToListUserDB(listUser))
    }

    override suspend fun updateUserSelectionStatus(status : Boolean) {
        userDao.updateUserSelectionStatus(status = if (status) 1 else 0)
    }

}