package com.justo.user.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TimezoneDTO (
    val offset : String,
    val description : String
)