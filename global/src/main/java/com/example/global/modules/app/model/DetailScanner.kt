package com.example.global.modules.app.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class DetailScanner(
    @Json(name = "products")
    var products: List<Product>?,
    @Json(name = "status")
    var status: Boolean?,
    @Json(name = "user")
    var user: User?
):Parcelable