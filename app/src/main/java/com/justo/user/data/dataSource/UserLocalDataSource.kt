package com.justo.user.data.dataSource

import com.justo.user.data.db.dao.UserDao
import com.justo.user.data.mapper.IMapper
import com.justo.user.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface IUserLocalDataSource {
    suspend fun deleteUsersSelected()
    fun getUsers() : Flow<List<User>>
    suspend fun saveUsers(listUser: List<User>)
    suspend fun updateUserSelectionStatus(status : Boolean)
    suspend fun updateUserChecked(id: Int, isChecked: Boolean)
}

class UserLocalDataSource @Inject constructor(
    private val mapper: IMapper,
    private val userDao: UserDao
) : IUserLocalDataSource {

    override suspend fun deleteUsersSelected() {
        userDao.deleteUsersSelected()
    }

    override fun getUsers(): Flow<List<User>> {
        return userDao.getUsers().map {
            mapper.mapListUserDBToListUser(listUserDB = it)
        }
    }

    override suspend fun saveUsers(listUser: List<User>) {
        userDao.insertUsers(mapper.mapListUserToListUserDB(listUser))
    }

    override suspend fun updateUserSelectionStatus(status : Boolean) {
        if (!status) {
            userDao.cleanUsersChecked()
        }
        userDao.updateUserSelectionStatus(status = if (status) 1 else 0)
    }

    override suspend fun updateUserChecked(id: Int, isChecked: Boolean) {
        userDao.updateUserChecked(id = id, isChecked = if (isChecked) 1 else 0)
    }

}