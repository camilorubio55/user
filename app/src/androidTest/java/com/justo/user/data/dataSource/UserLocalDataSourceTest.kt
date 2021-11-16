package com.justo.user.data.dataSource

import com.justo.user.data.db.dao.UserDao
import com.justo.user.data.db.entities.UserDB
import com.justo.user.data.mapper.IMapper
import com.justo.user.domain.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class UserLocalDataSourceTest {

    private val mockUserDao: UserDao =
        Mockito.mock(UserDao::class.java)
    private val mockMapper: IMapper =
        Mockito.mock(IMapper::class.java)

    private val userLocalDataSource = UserLocalDataSource(
        mockMapper,
        mockUserDao
    )

    @After
    fun after() {
        Mockito.verifyNoMoreInteractions(
            mockMapper,
            mockUserDao
        )
    }

    @Test
    fun deleteUsersSelected() {
        runBlocking {
            userLocalDataSource.deleteUsersSelected()

            Mockito.verify(mockUserDao).deleteUsersSelected()
        }
    }

    @Test
    fun saveUsers() {
        runBlocking {
            val listUser = arrayListOf(User(1,"","","",""))
            val listUserDB = arrayListOf(UserDB(1,"","","",""))
            Mockito.`when`(mockMapper.mapListUserToListUserDB(listUser)).thenReturn(listUserDB)

            userLocalDataSource.saveUsers(listUser = listUser)

            Mockito.verify(mockMapper).mapListUserToListUserDB(listUser)
            Mockito.verify(mockUserDao).insertUsers(listUserDB)
        }
    }

    @Test
    fun updateUserSelectionStatus_whenStatusIsFalse() {
        runBlocking {
            userLocalDataSource.updateUserSelectionStatus(status = false)

            Mockito.verify(mockUserDao).cleanUsersChecked()
            Mockito.verify(mockUserDao).updateUserSelectionStatus(status = 0)
        }
    }

    @Test
    fun updateUserSelectionStatus_whenStatusIsTrue() {
        runBlocking {
            userLocalDataSource.updateUserSelectionStatus(status = true)

            Mockito.verify(mockUserDao).updateUserSelectionStatus(status = 1)
        }
    }

    @Test
    fun updateUserChecked_whenIsCheckedTrue() {
        runBlocking {
            userLocalDataSource.updateUserChecked(id = 1, isChecked = true)

            Mockito.verify(mockUserDao).updateUserChecked(id = 1, isChecked = 1)
        }
    }

    @Test
    fun updateUserChecked_whenIsCheckedFalse() {
        runBlocking {
            userLocalDataSource.updateUserChecked(id = 1, isChecked = false)

            Mockito.verify(mockUserDao).updateUserChecked(id = 1, isChecked = 0)
        }
    }

}