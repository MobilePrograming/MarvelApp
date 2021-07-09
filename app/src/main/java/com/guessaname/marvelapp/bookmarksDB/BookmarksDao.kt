package com.guessaname.marvelapp.bookmarksDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookmarksDao {
    @Insert
    fun insert(bookmark: Bookmark)

    @Update
    fun update(bookmark: Bookmark)

    @Query("SELECT * from bookmarks_list WHERE bookmarkId = :key")
    fun get(key: Long): Bookmark?

    @Query("DELETE FROM  bookmarks_list")
    fun clear()

    @Query("SELECT * FROM bookmarks_list ORDER BY bookmarkId DESC LIMIT 1")
    fun getTonight(): Bookmark?

    @Query("SELECT * FROM bookmarks_list ORDER BY bookmarkId DESC")
    fun getAllNights(): LiveData<List<Bookmark>>
}