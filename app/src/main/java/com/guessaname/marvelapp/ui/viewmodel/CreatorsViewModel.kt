package com.guessaname.marvelapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guessaname.marvelapp.data.model.CreatorResponse
import com.guessaname.marvelapp.data.repository.CreatorsRepository
import com.guessaname.marvelapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CreatorsViewModel(
    val creatorsRepository: CreatorsRepository
):ViewModel(){

    val creators: MutableLiveData<Resource<CreatorResponse>> = MutableLiveData()

    init {
        getCreators()
    }
    fun getCreators() = viewModelScope.launch {
        creators.postValue(Resource.Loading())
        val response = creatorsRepository.getCreators()
        creators.postValue(handleResponse(response))
    }

    private fun handleResponse(response: Response<CreatorResponse>): Resource<CreatorResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}