package com.example.global.modules.app.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Product(
    @Json(name = "good")
    var good: Good?,
    @Json(name = "good_id")
    var goodId: Int?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "products")
    var products: List<ProductX>?,
    @Json(name = "property_number")
    var propertyNumber: String?
):Parcelable