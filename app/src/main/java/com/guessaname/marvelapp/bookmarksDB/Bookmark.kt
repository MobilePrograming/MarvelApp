package com.guessaname.marvelapp.bookmarksDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks_list")
data class Bookmark(
    @PrimaryKey(autoGenerate = true)
    var bookmarkId: Int,

    @ColumnInfo(name = "character_id")
    var characterId: Int
)