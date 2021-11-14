package com.justo.user.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IdDTO (
    val name : String,
    val value : String? = ""
)