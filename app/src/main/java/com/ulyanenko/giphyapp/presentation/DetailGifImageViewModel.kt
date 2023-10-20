package com.ulyanenko.giphyapp.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulyanenko.giphyapp.data.repositoryImpl.GifImageRepositoryImpl
import com.ulyanenko.giphyapp.domain.GetGifImagesFromDbUseCase
import com.ulyanenko.giphyapp.domain.GifImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailGifImageViewModel @Inject constructor (private val getGifs: GetGifImagesFromDbUseCase) : ViewModel() {

    private val _gif: MutableStateFlow<List<GifImage>> = MutableStateFlow(listOf())
    val gif: StateFlow<List<GifImage>> = _gif.asStateFlow()

    init {
        viewModelScope.launch {
            val listGifs = getGifs.getGifFromDb()
            _gif.value = listGifs
        }

    }


}