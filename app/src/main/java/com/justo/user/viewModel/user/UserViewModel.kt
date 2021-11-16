package com.justo.user.viewModel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justo.user.domain.models.User
import com.justo.user.domain.useCases.IDeleteUsersSelectedUseCase
import com.justo.user.domain.useCases.IGetUsersUseCase
import com.justo.user.domain.useCases.IUpdateUserListUseCase
import com.justo.user.domain.useCases.IUpdateUserCheckedUseCase
import com.justo.user.domain.useCases.IUpdateUserSelectionStatusUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val deleteUsersSelectedUseCase: IDeleteUsersSelectedUseCase,
    private val updateUserCheckedUseCase: IUpdateUserCheckedUseCase,
    private val updateUserSelectionStatusUseCase: IUpdateUserSelectionStatusUseCase,
    private val updateUserListUseCase: IUpdateUserListUseCase,
    getUsers: IGetUsersUseCase
)  : ViewModel() {

    private var _users: LiveData<List<User>> = getUsers.invoke()
    val users: LiveData<List<User>>
        get() = _users

    fun deleteUsersSelected() {
        viewModelScope.launch {
            deleteUsersSelectedUseCase.invoke()
        }
    }

    fun updateUserListUseCase() {
        viewModelScope.launch {
            updateUserListUseCase.invoke()
        }
    }

    fun updateUserChecked(id : Int, isChecked : Boolean) {
        viewModelScope.launch {
            updateUserCheckedUseCase.invoke(id = id, isChecked = isChecked)
        }
    }

    fun updateUserSelectionStatus(status : Boolean) {
        viewModelScope.launch {
            updateUserSelectionStatusUseCase.invoke(status = status)
        }
    }

}