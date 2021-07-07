package com.guessaname.marvelapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Creator (
    @SerializedName("id")
    @Expose
    var creatorid: Int? = null,
    @SerializedName("fullName")
    @Expose
    var creatorname: String? = null,
    @SerializedName("thumbnail")
    @Expose
    var creatorthumbnail: Thumbnail? = null,
    @Expose
    val creatorimages:List<Images>
) :Serializable