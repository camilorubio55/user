package com.justo.user.viewModel.user

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justo.user.domain.useCases.IGetUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val getUserUseCase: IGetUserUseCase
)  : ViewModel() {

    fun getUser() {
        viewModelScope.launch {
            val response = getUserUseCase.invoke()
            Log.d("---Response Service", response)
        }
    }

}