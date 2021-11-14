package com.justo.user.utility

import android.util.Log
import com.justo.user.data.dto.ResponseDTO
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.NullPointerException

fun <T : Any> Response<T>.callServices(): Result<T> {
    if (this.isSuccessful) {
        return if (this.body() != null) {
            Result.Success(this.body() as T)
        } else {
            Result.Error(NullPointerException(this.errorBody().toString()))
        }
    }
    return Result.Error(HttpException(this))
}

suspend fun <T : Any> safeApiCall(
    call: suspend () -> Result<T>,
    errorMessage: String
): Result<T> = try {
    call.invoke()
} catch (e: Exception) {
    Log.e("safeApiCall", e.message.toString())
    Result.Error(IOException(errorMessage, e))
}

fun <T : Any> Result<ResponseDTO<T>>.validateResponse(): Result<T> {
    return try {
        when (this) {
            is Result.Success -> Result.Success(this.data.results)
            is Result.Error -> Result.Error(AssertionError("Error msg"))
        }
    } catch (e: Throwable) {
        e.printStackTrace()
        Result.Error(e)
    }
}
