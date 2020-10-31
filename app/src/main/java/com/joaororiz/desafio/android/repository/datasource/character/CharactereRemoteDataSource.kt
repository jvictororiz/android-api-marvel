package com.joaororiz.desafio.android.repository.datasource.character

import com.joaororiz.desafio.android.data.entities.Character
import com.joaororiz.desafio.android.data.entities.Comic
import com.joaororiz.desafio.android.data.entities.GlobalResponse
import io.reactivex.Single

interface CharacterRemoteDataSource {
    fun listAll(limit: Int, offset: Int): Single<GlobalResponse<Character>>
    fun findComicsByCharacter(id: Int): Single<GlobalResponse<Comic>>
}