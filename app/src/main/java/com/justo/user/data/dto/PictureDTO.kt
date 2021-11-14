package com.justo.user.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PictureDTO (
    val large : String,
    val medium : String,
    val thumbnail : String
)