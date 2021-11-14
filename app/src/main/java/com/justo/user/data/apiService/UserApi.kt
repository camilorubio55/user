package com.justo.user.data.apiService

import com.justo.user.data.dto.ResponseDTO
import com.justo.user.data.dto.UserDTO
import com.justo.user.utility.Constants.UserApi.USER
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {

    @GET(USER)
    suspend fun getUser() : Response<ResponseDTO<List<UserDTO>>>

}