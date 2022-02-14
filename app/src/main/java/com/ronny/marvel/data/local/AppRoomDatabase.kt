package com.ronny.marvel.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ronny.marvel.data.local.marvel.converter.DataConverter
import com.ronny.marvel.data.local.marvel.dao.MarvelDataDao
import com.ronny.marvel.data.local.marvel.entity.*

@Database(
    entities = [CharacterEntity::class, ComicsEntity::class, DataEntity::class,
        ItemEntity::class, MarvelDataEntity::class,
        StoriesEntity::class, ThumbnailEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun marvelDataDao(): MarvelDataDao

    companion object {
        private const val DATABASE_NAME = "RoomDatabase.dp"

        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppRoomDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}