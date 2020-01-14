package com.example.memorygame.modules.home.model

import android.os.Parcelable
import androidx.annotation.NonNull
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
class Image : Parcelable {
    @NonNull
    @IgnoredOnParcel
    var id: Long = 0
    @IgnoredOnParcel
    var src: String? = ""
}