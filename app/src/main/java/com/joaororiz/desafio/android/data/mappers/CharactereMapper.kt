package com.joaororiz.desafio.android.data.mappers

import com.joaororiz.desafio.android.data.entities.Character
import com.joaororiz.desafio.android.data.entities.Comic
import com.joaororiz.desafio.android.data.entities.Thumbnail
import com.joaororiz.desafio.android.repository.datasource.character.local.entity.CharacterEntity
import com.joaororiz.desafio.android.repository.datasource.character.local.entity.ComicEntity
import com.joaororiz.desafio.android.repository.datasource.character.local.entity.ThumbnailEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
interface CharacterMapper {

    @Mappings
    fun convertToCharacter(characterEntity: CharacterEntity): Character
    @Mappings
    fun convertToComic(characterEntity: ComicEntity): Comic
    @Mappings
    fun convertToComicEntity(character: Comic): ComicEntity
    @Mappings
    fun convertCharacterEntity(character: Character): CharacterEntity

    @Mappings
    fun convertToThumbnail(thumbnail: ThumbnailEntity): Thumbnail

    @Mappings
    fun convertToThumbnailEntity(thumbnail: Thumbnail): ThumbnailEntity


}