package com.example.global.modules.app.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class ProductXX(
    @Json(name = "good_properties_id")
    var goodPropertiesId: Int?,
    @Json(name = "good_property")
    var goodProperty: GoodProperty?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "value")
    var value: String?
): Parcelable