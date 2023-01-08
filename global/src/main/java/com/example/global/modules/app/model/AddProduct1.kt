package com.example.global.modules.app.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class AddProduct1(
    @Json(name = "result")
    var result: Result?,
    @Json(name = "status")
    var status: Boolean?
):Parcelable