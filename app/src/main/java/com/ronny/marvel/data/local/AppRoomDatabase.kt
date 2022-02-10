package com.ronny.marvel.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ronny.marvel.data.local.marvel.entity.*
import com.ronny.marvel.data.remote.dto.MarvelDataDto

@Database(
    entities = [CharacterEntity::class, ComicsEntity::class, DataEntity::class,
        ItemEntity::class, ItemEntity::class, MarvelDataEntity::class,
        StoriesEntity::class, ThumbnailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun marvelDataDao(): MarvelDataDto

    companion object {
        private const val DATABASE_NAME = "RoomDatabase.dp"

        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppRoomDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}