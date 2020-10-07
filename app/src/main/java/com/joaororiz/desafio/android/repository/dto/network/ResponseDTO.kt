package com.joaororiz.desafio.android.repository.dto.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GlobalResponseDTO<T : Parcelable>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
) : Parcelable