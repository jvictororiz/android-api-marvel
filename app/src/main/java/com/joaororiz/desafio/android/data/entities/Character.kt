package com.joaororiz.desafio.android.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnail(
    val path: String?=null,
    val extension: String?=null
) : Parcelable

@Parcelize
data class Character(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val thumbnail: Thumbnail? = null
) : Parcelable