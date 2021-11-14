package com.justo.user.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DobDTO (
    val date : String,
    val age : Long
)