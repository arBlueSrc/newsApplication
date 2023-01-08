package com.example.global.modules.shared

import com.squareup.moshi.Json

data class ItemBottomSheet(
    @Json(name = "id")
    var id: Int?,
    @Json(name = "name")
    var name: String?
)
