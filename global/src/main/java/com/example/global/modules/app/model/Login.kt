package com.example.global.modules.app.model


import android.annotation.SuppressLint
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@SuppressLint("ParcelCreator")
@Parcelize
data class Login(
    @Json(name = "result")
    var result: String?,
    @Json(name = "status")
    var status: Boolean?
):Parcelable