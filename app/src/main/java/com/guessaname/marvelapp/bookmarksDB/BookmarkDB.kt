package com.guessaname.marvelapp.bookmarksDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Bookmark::class], version = 1)
abstract class BookmarkDB: RoomDatabase() {

    abstract val BookmarksDao: BookmarksDao

    companion object {

        @Volatile
        private var INSTANCE: BookmarkDB? = null

        fun getInstance(context: Context): BookmarkDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookmarkDB::class.java,
                        "sleep_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}