package com.guessaname.marvelapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Creator (

    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("fullName")
    @Expose
    var name: String? = null,
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: Thumbnail? = null,

) :Serializable