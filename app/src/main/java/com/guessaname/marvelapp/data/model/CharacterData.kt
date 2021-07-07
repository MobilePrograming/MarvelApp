package com.guessaname.marvelapp.data.model

import com.google.gson.annotations.SerializedName

data class CharacterData(
    val limit: Int,
    @SerializedName("results")
    val results: List<Character>? = null
)