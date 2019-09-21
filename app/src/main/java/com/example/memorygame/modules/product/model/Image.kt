package com.example.memory_game.modules.product.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
class Image : Parcelable {
    @Transient var id: String? = null
    @Transient var product_id: String? = null
    @Transient var src: String? = null
}