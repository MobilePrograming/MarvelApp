package com.guessaname.marvelapp.data.repository

import com.guessaname.marvelapp.data.api.ComicsInstance

class ComicsRepository {

    suspend fun getComics() = ComicsInstance.comicsapi.getComics()

}
