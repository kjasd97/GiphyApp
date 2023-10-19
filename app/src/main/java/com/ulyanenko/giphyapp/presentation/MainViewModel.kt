package com.ulyanenko.giphyapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ulyanenko.giphyapp.data.mapper.GifImageMapper
import com.ulyanenko.giphyapp.data.model.DataObjectDto
import com.ulyanenko.giphyapp.data.network.ApiFactory
import com.ulyanenko.giphyapp.domain.GifImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _gifs: MutableStateFlow<List<GifImage>> = MutableStateFlow(listOf())
    val gifs: StateFlow<List<GifImage>> = _gifs.asStateFlow()

    private val apiService = ApiFactory.apiService

    private val mapper = GifImageMapper()
    init {
        loadGifs()
    }

    private fun loadGifs() {
        viewModelScope.launch {
            try {
                val listDto = apiService.loadImages().res.map {
                    it.images.gifImage
                }
                val response = mapper.mapResponseToGifImage(listDto)
                _gifs.value = response
            } catch (e: Exception) {
                _gifs.value = emptyList()
            }
        }
    }
}