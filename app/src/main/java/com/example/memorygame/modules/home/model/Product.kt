package com.example.memorygame.modules.home.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
class Product : Parcelable {
    @Transient
    var id: Any? = null
    @Transient
    var image: Image? = null
}