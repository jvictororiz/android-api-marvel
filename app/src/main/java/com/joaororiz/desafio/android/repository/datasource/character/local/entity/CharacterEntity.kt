package com.joaororiz.desafio.android.repository.datasource.character.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joaororiz.desafio.android.data.entities.Thumbnail

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    var id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    @Embedded
    var thumbnail: ThumbnailEntity? = null
)


