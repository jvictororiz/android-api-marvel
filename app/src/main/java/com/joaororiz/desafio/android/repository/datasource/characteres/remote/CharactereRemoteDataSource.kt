package com.joaororiz.desafio.android.repository.datasource.characteres.remote

import com.joaororiz.desafio.android.data.entities.Character
import com.joaororiz.desafio.android.data.entities.Comic
import com.joaororiz.desafio.android.data.entities.GlobalResponse
import com.joaororiz.desafio.android.data.service.MarvelService
import com.joaororiz.desafio.android.extension.backgroundCall
import com.joaororiz.desafio.android.repository.datasource.characteres.CharactereDataSource
import io.reactivex.Single

class CharacterRemoteDataSource(
    private val marvelService: MarvelService
) : CharactereDataSource {
    override fun listAll(limit: Int, offset: Int): Single<GlobalResponse<Character>> = marvelService.findCharacteres(limit, offset).backgroundCall()

    override fun findComicsByCharactere(id: Int): Single<GlobalResponse<Comic>> = marvelService.findComics(id).backgroundCall()

}