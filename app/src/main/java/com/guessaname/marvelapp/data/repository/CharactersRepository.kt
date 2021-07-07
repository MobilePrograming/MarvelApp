package com.guessaname.marvelapp.data.repository

import com.guessaname.marvelapp.data.api.CharactersInstance

class CharactersRepository {

    suspend fun getCharacters() = CharactersInstance.characterapi.getCharacters()

}
