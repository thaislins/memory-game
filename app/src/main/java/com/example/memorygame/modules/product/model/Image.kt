package com.example.memory_game.modules.product.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
class Image : Parcelable {
    @IgnoredOnParcel var id: String? = null
    @IgnoredOnParcel var product_id: String? = null
    @IgnoredOnParcel var src: String? = null
}