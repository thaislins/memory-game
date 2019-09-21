package com.example.memory_game.modules.product.model

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
class Product : Parcelable {
    var id: Any? = null
    var title: Any? = null
    var image: Image? = null
}