package com.example.memory_game.modules.product.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
class Image : Parcelable {
    var id: String? = null
    var product_id: String? = null
    var src: String? = null
}