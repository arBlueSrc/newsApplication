package com.example.global.modules.app.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Good(
    @Json(name = "id")
    var id: Int?,
    @Json(name = "name")
    var name: String?
):Parcelable