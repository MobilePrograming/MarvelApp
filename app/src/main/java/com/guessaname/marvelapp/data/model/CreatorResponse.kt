package com.guessaname.marvelapp.data.model

import com.google.gson.annotations.SerializedName

data class CreatorResponse (
    val copyright:String,
    @SerializedName("data")
    val creatorData: CreatorData?= null
)