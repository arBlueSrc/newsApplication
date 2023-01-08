package com.example.global.modules.app.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Result(
    @Json(name = "employees")
    var employees: List<Employee>?,
    @Json(name = "goods")
    var goods: List<GoodX>?,
    @Json(name = "parts")
    var parts: List<Part>?,
    @Json(name = "stores")
    var stores: List<Store>?
):Parcelable