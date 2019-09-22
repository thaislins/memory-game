package com.example.memorygame.modules.home.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
class Product : Parcelable {
    @IgnoredOnParcel var id: Any? = null
    @IgnoredOnParcel var image: Image? = null
}