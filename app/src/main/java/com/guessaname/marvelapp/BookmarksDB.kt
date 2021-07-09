package com.guessaname.marvelapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookmarksList::class], version = 1)
abstract class BookmarkDatabase : RoomDatabase() {
    abstract fun BookmarkDao(): BookmarksDao?
}