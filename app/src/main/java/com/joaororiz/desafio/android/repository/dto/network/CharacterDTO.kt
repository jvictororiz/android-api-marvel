package com.joaororiz.desafio.android.repository.dto.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ThumbnailDTO(
    val path: String,
    val extension: String
) : Parcelable

@Parcelize
data class CharacterDTO(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: ThumbnailDTO
) : Parcelable