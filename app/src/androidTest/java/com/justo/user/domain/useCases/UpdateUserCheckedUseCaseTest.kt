package com.justo.user.domain.useCases

import com.justo.user.data.repository.IUserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class UpdateUserCheckedUseCaseTest {

    private val mockUserRepository = Mockito.mock(IUserRepository::class.java)

    private val updateUserCheckedUseCase = UpdateUserCheckedUseCase(
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
        runBlocking {
            updateUserCheckedUseCase.invoke(id = 1, isChecked = true)

            Mockito.verify(mockUserRepository).updateUserChecked(id = 1, isChecked = true)
        }
    }

}