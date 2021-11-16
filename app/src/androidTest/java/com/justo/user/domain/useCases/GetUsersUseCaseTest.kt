package com.justo.user.domain.useCases

import androidx.lifecycle.MutableLiveData
import com.justo.user.data.repository.IUserRepository
import com.justo.user.domain.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class GetUsersUseCaseTest {

    private val mockUserRepository = Mockito.mock(IUserRepository::class.java)

    private val getUsersUseCase = GetUsersUseCase(
        mockUserRepository
    )

    @After
    fun after() {
        Mockito.verifyNoMoreInteractions(
            mockUserRepository
        )
    }

    @Test
    fun invoke() {
        val listUser = arrayListOf<User>()
        val liveDataListUser = MutableLiveData<List<User>>(listUser)
        Mockito.`when`(mockUserRepository.getUsers()).thenReturn(liveDataListUser)

        val result = getUsersUseCase.invoke()

        assertEquals(0, result.value?.size)
        Mockito.verify(mockUserRepository).getUsers()
    }
}