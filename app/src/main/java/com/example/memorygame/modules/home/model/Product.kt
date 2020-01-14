package com.example.memorygame.modules.home.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Entity // Defines class as an entity creating a table for it in the database
@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
class Product : Parcelable {
    @PrimaryKey
    @IgnoredOnParcel
    var id: Long = 0
    var imageSrc: String? = ""
    @Ignore
    @IgnoredOnParcel
    var image: Image? = null
}