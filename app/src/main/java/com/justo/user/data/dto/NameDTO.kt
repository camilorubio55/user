package com.justo.user.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NameDTO (
    val title : String,
    val first : String,
    val last : String
)