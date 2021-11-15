package com.justo.user.domain.models

data class User(
    val id: Int,
    val name: String,
    var email: String,
    val phone: String,
    val image: String
)