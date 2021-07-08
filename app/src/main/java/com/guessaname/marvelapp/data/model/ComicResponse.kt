package com.guessaname.marvelapp.data.model

import com.google.gson.annotations.SerializedName

data class ComicResponse (
    val copyright:String,
    @SerializedName("data")
    val comicData: ComicData?= null
)