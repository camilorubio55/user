package com.justo.user.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StreetDTO (
    val number : String,
    val name : String
)