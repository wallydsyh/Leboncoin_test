package com.leboncoin.test.wallyd.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leboncoin.test.wallyd.model.AlbumsModel

@Database(entities = [AlbumsModel::class], version = 1, exportSchema = false)
abstract class AlbumDataBase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        var INSTANCE: AlbumDataBase? = null

        fun getDatabase(
            context: Context
        ): AlbumDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE
                ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AlbumDataBase::class.java,
                    "album_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}