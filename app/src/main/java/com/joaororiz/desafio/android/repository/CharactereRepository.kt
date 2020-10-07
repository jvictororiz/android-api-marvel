package com.joaororiz.desafio.android.repository

import com.joaororiz.desafio.android.data.entities.Character
import com.joaororiz.desafio.android.data.entities.Comic
import com.joaororiz.desafio.android.data.entities.GlobalResponse
import com.joaororiz.desafio.android.repository.datasource.characteres.CharactereDataSource
import io.reactivex.Single

interface CharactereRepository {
    fun listAll(limit: Int, offset: Int): Single<GlobalResponse<Character>>
    fun findComicsByCharactere(id: Int): Single<GlobalResponse<Comic>>

    class CharactereRepositoryImpl(private val dataSource: CharactereDataSource) : CharactereRepository {
        override fun listAll(limit: Int, offset: Int) = dataSource.listAll(limit, offset)
        override fun findComicsByCharactere(id: Int) = dataSource.findComicsByCharactere(id)
    }
}


