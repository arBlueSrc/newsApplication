package com.example.global.modules.app.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddProductResult(
    @Json(name = "result")
    var result: String?,
    @Json(name = "status")
    var status: Boolean?
)