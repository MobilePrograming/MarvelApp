package com.guessaname.marvelapp.data.repository

import com.guessaname.marvelapp.data.api.CharactersInstance
import com.guessaname.marvelapp.data.api.IronManInstance

class CharactersRepository {

    suspend fun getThor() = CharactersInstance.thorapi.getCharacters()

    suspend fun getIronMan() = IronManInstance.ironmanapi.getCharacters()

}
