package com.justo.user.domain.useCases

import com.justo.user.data.repository.IUserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class DeleteUsersSelectedUseCaseTest {

    private val mockUserRepository = Mockito.mock(IUserRepository::class.java)

    private val deleteUsersSelectedUseCase = DeleteUsersSelectedUseCase(
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
            deleteUsersSelectedUseCase.invoke()

            Mockito.verify(mockUserRepository).deleteUsersSelected()
        }
    }

}