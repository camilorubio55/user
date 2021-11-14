package com.justo.user.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoordinatesDTO (
    val latitude : String,
    val longitude : String
)