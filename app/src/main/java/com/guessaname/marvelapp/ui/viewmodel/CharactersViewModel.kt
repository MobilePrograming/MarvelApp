package com.guessaname.marvelapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guessaname.marvelapp.data.model.CharacterResponse
import com.guessaname.marvelapp.data.repository.CharactersRepository
import com.guessaname.marvelapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CharactersViewModel(
    val charactersRepository: CharactersRepository
):ViewModel(){

    val characters: MutableLiveData<Resource<CharacterResponse>> = MutableLiveData()

    init {
        getCharacters()
    }
    fun getCharacters() = viewModelScope.launch {
        characters.postValue(Resource.Loading())
        val response = charactersRepository.getCharacters()
        characters.postValue(handleResponse(response))
    }

    private fun handleResponse(response: Response<CharacterResponse>): Resource<CharacterResponse>{
        if (response.isSuccessful){
           response.body()?.let { resultResponse ->
               return Resource.Success(resultResponse)
           }
        }
        return Resource.Error(response.message())
    }

}