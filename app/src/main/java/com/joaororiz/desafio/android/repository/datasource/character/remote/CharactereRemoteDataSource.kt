package com.joaororiz.desafio.android.repository.datasource.characters.remote

import com.joaororiz.desafio.android.data.entities.Character
import com.joaororiz.desafio.android.data.entities.Comic
import com.joaororiz.desafio.android.data.entities.GlobalResponse
import com.joaororiz.desafio.android.data.service.MarvelService
import com.joaororiz.desafio.android.extension.backgroundCall
import com.joaororiz.desafio.android.repository.datasource.character.CharacterRemoteDataSource
import io.reactivex.Single

class CharacterRemoteRemoteDataSource(
    private val marvelService: MarvelService
) : CharacterRemoteDataSource {
    override fun listAll(limit: Int, offset: Int): Single<GlobalResponse<Character>> = marvelService.findCharacters(limit, offset).backgroundCall()

    override fun findComicsByCharacter(id: Int): Single<GlobalResponse<Comic>> = marvelService.findComics(id).backgroundCall()

}