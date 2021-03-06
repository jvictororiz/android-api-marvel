package com.joaororiz.desafio.android.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Response<T>(
    val data: T
)

@Parcelize
data class GlobalResponse<T : Parcelable>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
) : Parcelable