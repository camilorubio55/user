package com.justo.user.data.repository

import com.justo.user.data.dataSource.IUserLocalDataSource
import com.justo.user.data.dataSource.IUserRemoteDataSource
import com.justo.user.domain.models.User
import com.justo.user.utility.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class UserRepositoryTest {

    private val mockUserLocalDataSource = Mockito.mock(IUserLocalDataSource::class.java)
    private val mockUserRemoteDataSource = Mockito.mock(IUserRemoteDataSource::class.java)

    private val userRepository = UserRepository(
        mockUserLocalDataSource,
        mockUserRemoteDataSource
    )

    @After
    fun after() {
        Mockito.verifyNoMoreInteractions(
            mockUserLocalDataSource,
            mockUserRemoteDataSource
        )
    }

    @Test
    fun deleteUsersSelected() {
        runBlocking {
            userRepository.deleteUsersSelected()

            Mockito.verify(mockUserLocalDataSource).deleteUsersSelected()
        }
    }

    @Test
    fun updateListUser_whenResultIsSuccess() {
        runBlocking {
            val listUser = arrayListOf(User(1,"","","",""))
            Mockito.`when`(mockUserRemoteDataSource.getUsers()).thenReturn(Result.Success(listUser))

            userRepository.updateListUser()

            Mockito.verify(mockUserRemoteDataSource).getUsers()
            Mockito.verify(mockUserLocalDataSource).saveUsers(listUser)
        }
    }

    @Test
    fun updateListUser_whenResultIsError() {
        runBlocking {
            Mockito.`when`(mockUserRemoteDataSource.getUsers()).thenReturn(Result.Error(Exception()))

            userRepository.updateListUser()

            Mockito.verify(mockUserRemoteDataSource).getUsers()
        }
    }

    @Test
    fun updateUserChecked() {
        runBlocking {
            userRepository.updateUserChecked(id = 1, isChecked = true)

            Mockito.verify(mockUserLocalDataSource).updateUserChecked(id = 1, isChecked = true)
        }
    }

    @Test
    fun updateUserSelectionStatus() {
        runBlocking {
            userRepository.updateUserSelectionStatus(status = true)

            Mockito.verify(mockUserLocalDataSource).updateUserSelectionStatus(status = true)
        }
    }

}