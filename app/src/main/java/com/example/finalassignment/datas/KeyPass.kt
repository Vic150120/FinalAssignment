package com.example.finalassignment.datas

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// Generates Moshi Adapter to convert JSON to Kotlin objects
@JsonClass(generateAdapter = true)

data class KeyPass(
    // Return a String: "keypass"
    @Json(name = "keypass") val keypass: String
)