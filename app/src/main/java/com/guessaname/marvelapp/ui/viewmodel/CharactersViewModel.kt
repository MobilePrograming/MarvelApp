package com.guessaname.marvelapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guessaname.marvelapp.data.model.CharacterResponse
import com.guessaname.marvelapp.data.repository.CharactersRepository
import com.guessaname.marvelapp.utils.Resource
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import retrofit2.Response

class CharactersViewModel(val charactersRepository: CharactersRepository):ViewModel(){

    val characters: MutableLiveData<Resource<CharacterResponse>> = MutableLiveData()
    //val responses = emptyList<Any>()
    //val responses by lazy { mutableListOf<Response>() }

    init {
        getCharacters()

    }
    fun getCharacters() = viewModelScope.launch {
        characters.postValue(Resource.Loading())
        val response = charactersRepository.getThor()
       // val response2 = charactersRepository.getIronMan()

        characters.postValue(handleResponse(response))
    }

    private fun handleResponse(responses: Response<CharacterResponse>): Resource<CharacterResponse>{
        if (responses.isSuccessful) {
          responses.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(responses.message())
    }

}