package com.justo.user.domain.useCases

import com.justo.user.data.repository.IUserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class UpdateUserSelectionStatusUseCaseTest {

    private val mockUserRepository = Mockito.mock(IUserRepository::class.java)

    private val updateUserSelectionStatusUseCase = UpdateUserSelectionStatusUseCase(
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
            updateUserSelectionStatusUseCase.invoke(status = true)

            Mockito.verify(mockUserRepository).updateUserSelectionStatus(status = true)
        }
    }

}