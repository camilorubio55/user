package com.justo.user.data.dataSource

import com.justo.user.data.apiService.UserApi
import com.justo.user.data.mapper.IMapper
import com.justo.user.domain.models.User
import com.justo.user.utility.Constants
import com.justo.user.utility.Result
import com.justo.user.utility.safeApiCall
import com.justo.user.utility.callServices
import com.justo.user.utility.validateResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface IUserRemoteDataSource {
    suspend fun getUsers() : Result<List<User>>
}

class UserRemoteDataSource @Inject constructor(
    private val mapper: IMapper,
    private val userApi: UserApi
) : IUserRemoteDataSource {

    override suspend fun getUsers(): Result<List<User>> {
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
                    val listUsers = result.data
                    Result.Success(mapper.mapListUserDTOToListUser(listUsers))
                }
                is Result.Error -> Result.Error(result.exception)
            }
        }
    }

}