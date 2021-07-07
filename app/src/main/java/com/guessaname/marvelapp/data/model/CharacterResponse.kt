package com.guessaname.marvelapp.data.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse (
    val copyright:String,
    @SerializedName("data")
    val characterData: CharacterData?= null
)