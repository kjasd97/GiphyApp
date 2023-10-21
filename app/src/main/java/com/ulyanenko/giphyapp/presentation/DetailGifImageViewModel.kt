package com.ulyanenko.giphyapp.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulyanenko.giphyapp.data.repositoryImpl.GifImageRepositoryImpl
import com.ulyanenko.giphyapp.domain.GetGifImagesBySearchUseCase
import com.ulyanenko.giphyapp.domain.GetGifImagesFromDbUseCase
import com.ulyanenko.giphyapp.domain.GifImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailGifImageViewModel @Inject constructor(
    private val getGifs: GetGifImagesFromDbUseCase,
    private val loadGifsBySearch: GetGifImagesBySearchUseCase
) :
    ViewModel() {

    private val _gif: MutableStateFlow<List<GifImage>> = MutableStateFlow(listOf())
    val gif: StateFlow<List<GifImage>> = _gif.asStateFlow()


    fun loadGifs() {
        viewModelScope.launch {
            val listGifs = getGifs.getGifFromDb()
            _gif.value = listGifs
        }
    }

    fun loadGifsBySearch(url: String) {
        viewModelScope.launch {
            val listGifs = loadGifsBySearch.getGifImagesBySearch(url)
            _gif.value = listGifs
        }
    }


}