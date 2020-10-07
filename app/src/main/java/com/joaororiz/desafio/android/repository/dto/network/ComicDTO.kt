package com.joaororiz.desafio.android.repository.dto.network

import android.os.Parcelable
import com.google.gson.annotations.JsonAdapter
import com.joaororiz.desafio.android.data.deserializer.TypePriceDeserializer
import kotlinx.android.parcel.Parcelize

enum class TypePrice(val code: String) {
    PRINT_PRICE("printPrice"),
    DIGITAL_PURCHASE_PRICE("digitalPurchasePrice")
}

@Parcelize
data class ComicPriceDTO(
    @JsonAdapter(TypePriceDeserializer::class)
    val type: TypePrice,
    val price: Float
) : Parcelable

@Parcelize
data class ComicDTO(
    val title: String,
    val description: String?="",
    val thumbnail: ThumbnailDTO,
    val prices: List<ComicPriceDTO>
) : Parcelable