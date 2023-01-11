package com.example.global.modules.app.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddProduct2(
    @Json(name = "result")
    var result: List<ResultX>?,
    @Json(name = "status")
    var status: Boolean?
)