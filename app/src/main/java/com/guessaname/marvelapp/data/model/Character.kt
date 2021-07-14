package com.guessaname.marvelapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Character (
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("thumbnail")
    @Expose
    var tumbnail: Thumbnail? = null,
    @SerializedName("description")
    @Expose
    var description: String? = null,
) : Serializable
