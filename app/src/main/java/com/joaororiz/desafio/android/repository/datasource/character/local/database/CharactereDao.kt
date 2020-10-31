package com.joaororiz.desafio.android.repository.datasource.character.local.database

import androidx.room.*
import com.joaororiz.desafio.android.repository.datasource.character.local.entity.CharacterEntity
import com.joaororiz.desafio.android.repository.datasource.character.local.entity.ComicEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CharacterDao {

    @Transaction
    @Query("Select * from characters")
    fun getCharacters(): Single<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCharacters(episodes: List<CharacterEntity>):Completable

}