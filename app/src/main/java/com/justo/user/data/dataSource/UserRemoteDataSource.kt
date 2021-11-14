package com.justo.user.data.dataSource

import com.justo.user.data.apiService.UserApi
import com.justo.user.utility.Constants
import com.justo.user.utility.Result
import com.justo.user.utility.safeApiCall
import com.justo.user.utility.callServices
import com.justo.user.utility.validateResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface IUserRemoteDataSource {
    suspend fun getUser() : Result<String>
}

class UserRemoteDataSource @Inject constructor(
    private val userApi: UserApi
) : IUserRemoteDataSource {

    override suspend fun getUser(): Result<String> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall(
                call = {
                    userApi.getUser()
                        .callServices()
                        .validateResponse()
                },
                errorMessage = Constants.TypeErrors.CALL_WEB_SERVICE_FAILED
            )
            when (result) {
                is Result.Success -> {
                    Result.Success(result.data[0].name.toString())
                }
                is Result.Error -> Result.Error(result.exception)
            }
        }
    }

}