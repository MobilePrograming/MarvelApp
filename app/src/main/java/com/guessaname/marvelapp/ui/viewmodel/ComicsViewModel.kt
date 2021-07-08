package com.guessaname.marvelapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guessaname.marvelapp.data.model.ComicResponse
import com.guessaname.marvelapp.data.repository.ComicsRepository
import com.guessaname.marvelapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class ComicsViewModel(
    val comicsRepository: ComicsRepository
):ViewModel(){

    val comics: MutableLiveData<Resource<ComicResponse>> = MutableLiveData()

    init {
        getComics()
    }
    fun getComics() = viewModelScope.launch {
        comics.postValue(Resource.Loading())
        val response = comicsRepository.getComics()
        comics.postValue(handleResponse(response))
    }

    private fun handleResponse(response: Response<ComicResponse>): Resource<ComicResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}