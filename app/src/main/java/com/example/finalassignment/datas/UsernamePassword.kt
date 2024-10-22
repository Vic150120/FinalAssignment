package com.example.finalassignment.datas

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// Generates Moshi Adapter to convert JSON to Kotlin objects
@JsonClass(generateAdapter = true)

data class UsernamePassword(
    @Json(name = "username") val username: String,
    @Json(name = "password") val password: String
)