package com.justo.user.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InfoDTO (
    val seed : String,
    val results : String,
    val page : Int,
    val version : String
)