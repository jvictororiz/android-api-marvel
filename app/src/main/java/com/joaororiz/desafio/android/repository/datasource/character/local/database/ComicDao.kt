package com.joaororiz.desafio.android.repository.datasource.character.local.database

import androidx.room.*
import com.joaororiz.desafio.android.repository.datasource.character.local.entity.CharacterEntity
import com.joaororiz.desafio.android.repository.datasource.character.local.entity.ComicEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ComicDao {

    @Transaction
    @Query("Select * from comic where idCharacterEntity =:idCharacterEntity")
    fun getComics(idCharacterEntity:Int): Single<List<ComicEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addComics(episodes: List<ComicEntity>):Completable

}