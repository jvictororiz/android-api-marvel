package com.joaororiz.desafio.android.repository.datasource.characteres

import com.joaororiz.desafio.android.data.entities.Character
import com.joaororiz.desafio.android.data.entities.Comic
import com.joaororiz.desafio.android.data.entities.GlobalResponse
import io.reactivex.Single

interface CharactereDataSource {
    fun listAll(limit: Int, offset: Int): Single<GlobalResponse<Character>>
    fun findComicsByCharactere(id: Int): Single<GlobalResponse<Comic>>
}