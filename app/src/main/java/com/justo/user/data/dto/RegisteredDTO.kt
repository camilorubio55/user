package com.justo.user.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisteredDTO (
    val date : String,
    val age : Long
)