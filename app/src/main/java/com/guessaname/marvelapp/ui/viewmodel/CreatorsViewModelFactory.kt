package com.guessaname.marvelapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.guessaname.marvelapp.data.repository.CreatorsRepository

class CreatorsViewModelFactory(
    val creatorsRepository: CreatorsRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreatorsViewModel(creatorsRepository) as T
    }
}