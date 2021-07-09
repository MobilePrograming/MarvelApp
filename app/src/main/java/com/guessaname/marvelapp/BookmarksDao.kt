package com.guessaname.marvelapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookmarksDao {
    @Insert
    fun addData(favoriteList: BookmarksList?)

    @get:Query("select * from bookmarklist")
    val favoriteData: kotlin.collections.List<BookmarksList?>?

    @Query("SELECT EXISTS (SELECT 1 FROM bookmarklist WHERE id=:id)")
    fun isFavorite(id: Int): Int

    @Delete
    fun delete(favoriteList: BookmarksList?)
}