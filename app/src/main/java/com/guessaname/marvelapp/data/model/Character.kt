package com.guessaname.marvelapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Character (
    @SerializedName("id")
    @Expose
    var characterid: Int? = null,
    @SerializedName("name")
    @Expose
    var charactername: String? = null,
    @SerializedName("thumbnail")
    @Expose
    var characterthumbnail: Thumbnail? = null,
    @SerializedName("description")
    @Expose
    var characterdescription: String? = null,
    val characterimages:List<Images>,
    //val comics:List<Comic>
) : Serializable
