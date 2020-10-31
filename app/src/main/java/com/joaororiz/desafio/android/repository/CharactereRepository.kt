package com.joaororiz.desafio.android.repository

import com.joaororiz.desafio.android.data.entities.Character
import com.joaororiz.desafio.android.data.entities.Comic
import com.joaororiz.desafio.android.data.mappers.CharacterMapper
import com.joaororiz.desafio.android.repository.datasource.character.CharacterRemoteDataSource
import com.joaororiz.desafio.android.repository.datasource.character.local.database.CharacterDao
import com.joaororiz.desafio.android.repository.datasource.character.local.database.ComicDao
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.mapstruct.factory.Mappers

interface CharacterRepository {
    fun listAll(limit: Int, offset: Int): Single<List<Character>>
    fun findComicsByCharacter(id: Int): Single<List<Comic>>
    fun listAllLocal(): Single<List<Character>>
    fun findComicsByCharacterLocal(id: Int): Single<List<Comic>>
    fun saveAllComics(idCharacter: Int, comics: List<Comic>): Completable
    fun saveAllCharacters(characters: List<Character>): Completable

    class CharacterRepositoryImpl(
        private val remoteDataSource: CharacterRemoteDataSource,
        private val localDataSource: CharacterDao,
        private val comicDao: ComicDao
    ) : CharacterRepository {
        override fun listAll(limit: Int, offset: Int) = remoteDataSource.listAll(limit, offset).map { it.results }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        override fun findComicsByCharacter(id: Int) = remoteDataSource.findComicsByCharacter(id).map { it.results }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        override fun listAllLocal() = localDataSource.getCharacters().map { list ->
            return@map list.map { Mappers.getMapper(CharacterMapper::class.java).convertToCharacter(it) }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


        override fun findComicsByCharacterLocal(id: Int) = comicDao.getComics(id).map { list ->
            return@map list.map { Mappers.getMapper(CharacterMapper::class.java).convertToComic(it) }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        override fun saveAllComics(idCharacter: Int, comics: List<Comic>): Completable {
            val list = comics.map {
                val comicEntity = Mappers.getMapper(CharacterMapper::class.java).convertToComicEntity(it)
                comicEntity.idCharacterEntity = idCharacter
                return@map comicEntity
            }
            return comicDao.addComics(list).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }

        override fun saveAllCharacters(characters: List<Character>): Completable {
            val list = characters.map {
                Mappers.getMapper(CharacterMapper::class.java).convertCharacterEntity(it)
            }
            return localDataSource.addCharacters(list).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}


