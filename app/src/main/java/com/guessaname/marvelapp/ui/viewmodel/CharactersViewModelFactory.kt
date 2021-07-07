package com.guessaname.marvelapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.guessaname.marvelapp.data.repository.CharactersRepository

class CharactersViewModelFactory(
    val charactersRepository: CharactersRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharactersViewModel(charactersRepository) as T
    }
}