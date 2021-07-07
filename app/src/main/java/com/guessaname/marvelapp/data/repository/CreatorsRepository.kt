package com.guessaname.marvelapp.data.repository

import com.guessaname.marvelapp.data.api.CreatorsInstance

class CreatorsRepository {

    suspend fun getCreators() = CreatorsInstance.creatorsapi.getCreators()

}