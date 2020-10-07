package com.joaororiz.desafio.android.useCase

import com.joaororiz.desafio.android.data.entities.Character
import com.joaororiz.desafio.android.data.entities.Comic
import com.joaororiz.desafio.android.data.entities.GlobalResponse
import com.joaororiz.desafio.android.repository.CharactereRepository
import io.reactivex.Single

interface CharacterUseCase {
    fun listAll(limit: Int, offset: Int): Single<GlobalResponse<Character>>
    fun findComicsByCharactere(id: Int): Single<GlobalResponse<Comic>>

    class CharacterUseCaseImpl(private val repository: CharactereRepository) : CharacterUseCase {
        override fun listAll(limit: Int, offset: Int): Single<GlobalResponse<Character>> {
            return repository.listAll(limit, offset)
        }

        override fun findComicsByCharactere(id: Int): Single<GlobalResponse<Comic>> {
            return repository.findComicsByCharactere(id)
        }
    }
}