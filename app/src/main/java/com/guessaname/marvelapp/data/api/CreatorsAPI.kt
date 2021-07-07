package com.guessaname.marvelapp.data.api

import com.guessaname.marvelapp.data.model.CreatorResponse
import com.guessaname.marvelapp.utils.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CreatorsAPI {

    @GET("creators")
    suspend fun getCreators(
        @Query("ts") apiKey: String = Constant.ts,
        @Query("apikey") ts: String = Constant.API_KEY,
        @Query("hash") hash: String = Constant.hash(),
        @Query("limit") limit: String = Constant.limit
    ):Response<CreatorResponse>
}