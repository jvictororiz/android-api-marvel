package com.joaororiz.desafio.android.repository.datasource.character.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joaororiz.desafio.android.repository.datasource.character.local.entity.CharacterEntity
import com.joaororiz.desafio.android.repository.datasource.character.local.entity.ComicEntity

@Database(entities = [CharacterEntity::class , ComicEntity::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    abstract fun comicDao(): ComicDao

}