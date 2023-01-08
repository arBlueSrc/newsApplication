package com.example.global.modules.app.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class User(
    @Json(name = "last_name")
    var lastName: String?,
    @Json(name = "name")
    var name: String?,
    @Json(name = "part")
    var part: String?,
    @Json(name = "store")
    var store: String?
):Parcelable