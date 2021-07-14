package com.guessaname.marvelapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Comic (
    @SerializedName("id")
    @Expose
    var comicid: Int? = null,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: Thumbnail? = null,
    @SerializedName("description")
    @Expose
    var description: String? = null,
    val images:List<Images>,

    ) : Serializable