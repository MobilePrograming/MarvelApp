package com.guessaname.marvelapp.data.model

import com.google.gson.annotations.SerializedName

data class CreatorData(
    val limit: Int,
    @SerializedName("results")
    val results: List<Creator>? = null
)