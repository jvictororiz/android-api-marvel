package com.joaororiz.desafio.android.repository.datasource.character.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comic")
data class ComicEntity(
    @PrimaryKey
    var idCharacterEntity: Int? = null,
    val title: String? = null,
    val description: String? = null,
    @Embedded
    val thumbnail: ThumbnailEntity? = null
)
