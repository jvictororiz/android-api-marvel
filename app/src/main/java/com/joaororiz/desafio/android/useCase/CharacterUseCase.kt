package com.joaororiz.desafio.android.useCase

import com.joaororiz.desafio.android.data.entities.Character
import com.joaororiz.desafio.android.data.entities.Comic
import com.joaororiz.desafio.android.data.entities.GlobalResponse
import com.joaororiz.desafio.android.repository.CharacterRepository
import io.reactivex.Completable
import io.reactivex.Single

interface CharacterUseCase {
    fun listAll(limit: Int, offset: Int): Single<List<Character>>
    fun findComicsByCharacter(id: Int): Single<List<Comic>>
    fun listAllLocal(): Single<List<Character>>
    fun findComicsByCharacterLocal(id: Int): Single<List<Comic>>
    fun saveAllComics(idCharacter: Int, comics: List<Comic>): Completable
    fun saveAllCharacters(characters: List<Character>): Completable

    class CharacterUseCaseImpl(private val repository: CharacterRepository) : CharacterUseCase {
        override fun listAll(limit: Int, offset: Int): Single<List<Character>> {
            return repository.listAll(limit, offset)
        }

        override fun findComicsByCharacter(id: Int): Single<List<Comic>> {
            return repository.findComicsByCharacter(id)
        }

        override fun saveAllComics(idCharacter: Int, comics: List<Comic>): Completable {
            return repository.saveAllComics(idCharacter, comics)
        }

        override fun saveAllCharacters(characters: List<Character>): Completable {
            return repository.saveAllCharacters(characters)
        }

        override fun listAllLocal() = repository.listAllLocal()

        override fun findComicsByCharacterLocal(id: Int) = repository.findComicsByCharacterLocal(id)
    }
}