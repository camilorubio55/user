package com.justo.user.viewModel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justo.user.domain.models.User
import com.justo.user.domain.useCases.IGetUsersUseCase
import com.justo.user.domain.useCases.IUpdateListUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val updateListUserUseCase: IUpdateListUserUseCase,
    getUsers: IGetUsersUseCase
)  : ViewModel() {

    private var _users: LiveData<List<User>?> = getUsers.invoke()
    val users: LiveData<List<User>?>
        get() = _users

    fun updateUserListUseCase() {
        viewModelScope.launch {
            updateListUserUseCase.invoke()
        }
    }

}