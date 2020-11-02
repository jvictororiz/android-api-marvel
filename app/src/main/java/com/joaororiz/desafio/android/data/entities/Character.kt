package com.joaororiz.desafio.android.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnail(
    var path: String?=null,
    var extension: String?=null
) : Parcelable

@Parcelize
data class Character(
    var id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    var thumbnail: Thumbnail? = null
) : Parcelable