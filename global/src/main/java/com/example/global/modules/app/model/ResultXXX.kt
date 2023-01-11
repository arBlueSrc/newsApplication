package com.example.global.modules.app.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultXXX(
    @Json(name = "id")
    var id: Int?,
    @Json(name = "last_name")
    var lastName: String?,
    @Json(name = "name")
    var name: String?
)