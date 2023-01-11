package com.example.global.modules.app.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Person(
    @Json(name = "result")
    var result: List<ResultXXX>?,
    @Json(name = "status")
    var status: Boolean?
)