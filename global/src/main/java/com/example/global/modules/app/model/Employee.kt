package com.example.global.modules.app.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Employee(
    @Json(name = "id")
    var id: Int?,
    @Json(name = "last_name")
    var lastName: String?,
    @Json(name = "name")
    var name: String?
):Parcelable