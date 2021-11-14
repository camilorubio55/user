package com.justo.user.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseDTO<T>(
    val results: T,
    val info : InfoDTO
)

