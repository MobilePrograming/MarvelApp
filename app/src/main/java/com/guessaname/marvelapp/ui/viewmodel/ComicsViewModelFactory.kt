package com.guessaname.marvelapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.guessaname.marvelapp.data.repository.ComicsRepository

class ComicsViewModelFactory(
    val comicsRepository: ComicsRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ComicsViewModel(comicsRepository) as T
    }
}