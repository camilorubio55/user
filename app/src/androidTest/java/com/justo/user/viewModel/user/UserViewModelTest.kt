package com.justo.user.viewModel.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.justo.user.domain.useCases.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class UserViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val mockDeleteUsersSelectedUseCase = Mockito.mock(IDeleteUsersSelectedUseCase::class.java)
    private val mockUpdateUserCheckedUseCase = Mockito.mock(IUpdateUserCheckedUseCase::class.java)
    private val mockUpdateUserSelectionStatusUseCase = Mockito.mock(IUpdateUserSelectionStatusUseCase::class.java)
    private val mockUpdateUserListUseCase = Mockito.mock(IUpdateUserListUseCase::class.java)
    private val mockGetUsersUseCase = Mockito.mock(IGetUsersUseCase::class.java)

    private val userViewModel = UserViewModel(
        mockDeleteUsersSelectedUseCase,
        mockUpdateUserCheckedUseCase,
        mockUpdateUserSelectionStatusUseCase,
        mockUpdateUserListUseCase,
        mockGetUsersUseCase
    )

    @After
    fun after() {
        Mockito.verifyNoMoreInteractions(
            mockDeleteUsersSelectedUseCase,
            mockUpdateUserCheckedUseCase,
            mockUpdateUserSelectionStatusUseCase,
            mockUpdateUserListUseCase,
            mockGetUsersUseCase
        )
    }

    @Test
    fun deleteUsersSelected() {
        runBlocking {
            userViewModel.deleteUsersSelected()

            Mockito.verify(mockDeleteUsersSelectedUseCase).invoke()
            Mockito.verify(mockGetUsersUseCase).invoke()
        }
    }

    @Test
    fun updateUserChecked() {
        runBlocking {
            userViewModel.updateUserChecked(id = 1, isChecked = true)

            Mockito.verify(mockUpdateUserCheckedUseCase).invoke(id = 1, isChecked = true)
            Mockito.verify(mockGetUsersUseCase).invoke()
        }
    }

    @Test
    fun updateUserSelectionStatus() {
        runBlocking {
            userViewModel.updateUserSelectionStatus(status = true)

            Mockito.verify(mockUpdateUserSelectionStatusUseCase).invoke(status = true)
            Mockito.verify(mockGetUsersUseCase).invoke()
        }
    }

}