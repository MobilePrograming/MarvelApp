package com.guessaname.marvelapp.data.api

import com.guessaname.marvelapp.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CharactersInstance {
    private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(Constant.AVENGERS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    }


    val thorapi by lazy {
        retrofit.create(CharactersAPI::class.java)
    }


    }
object IronManInstance{
    private val retrofit by lazy {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
    Retrofit.Builder()
        .baseUrl(Constant.IRONMAN_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
    }

    val ironmanapi by lazy {
        retrofit.create(CharactersAPI::class.java)
    }
}
