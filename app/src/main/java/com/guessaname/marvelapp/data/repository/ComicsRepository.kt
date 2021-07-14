package com.guessaname.marvelapp.data.repository

import com.guessaname.marvelapp.data.api.CharactersInstance
import com.guessaname.marvelapp.data.api.ComicsInstance

class ComicsRepository {

    suspend fun getComics(id: Int) = ComicsInstance.comicsapi.getComics(id)
}
