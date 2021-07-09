package com.guessaname.marvelapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="bookmarklist")
public class BookmarksList {
    @PrimaryKey
    private var id = 0

    @ColumnInfo(name = "image")
    private var image: String? = null

    @ColumnInfo(name = "prname")
    private var name: String? = null

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getImage(): String? {
        return image
    }

    fun setImage(image: String?) {
        this.image = image
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }
}