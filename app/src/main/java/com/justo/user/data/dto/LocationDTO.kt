package com.justo.user.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationDTO (
    val street : StreetDTO,
    val city : String,
    val state : String,
    val country : String,
    val postcode : Long,
    val coordinates : CoordinatesDTO,
    val timezone : TimezoneDTO
)