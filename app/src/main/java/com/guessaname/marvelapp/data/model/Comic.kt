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
    var comictitle: String? = null,
    @SerializedName("thumbnail")
    @Expose
    var comicthumbnail: Thumbnail? = null,
    @SerializedName("description")
    @Expose
    var comicdescription: String? = null,
    val comicimages:List<Images>,
    //val comics:List<Comic>
) : Serializable