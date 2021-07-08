package com.guessaname.marvelapp.data.model

import com.google.gson.annotations.SerializedName

data class ComicData(
    val limit: Int,
    @SerializedName("results")
    val results: List<Comic>? = null
)