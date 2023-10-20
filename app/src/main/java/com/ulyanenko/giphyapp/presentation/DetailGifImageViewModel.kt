package com.ulyanenko.giphyapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ulyanenko.giphyapp.data.database.GifImageEntity
import com.ulyanenko.giphyapp.data.repositoryImpl.GifImageRepositoryImpl
import com.ulyanenko.giphyapp.domain.AddGifImagesToDbUseCase
import com.ulyanenko.giphyapp.domain.DeleteGifImagesFromDbUseCase
import com.ulyanenko.giphyapp.domain.GetGifFromDbUseCase
import com.ulyanenko.giphyapp.domain.GetGifImagesFromDbUseCase
import com.ulyanenko.giphyapp.domain.GifImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailGifImageViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GifImageRepositoryImpl = GifImageRepositoryImpl(application)

    private val getGifs = GetGifImagesFromDbUseCase(repository)


    private val _gif: MutableStateFlow<List<GifImage>> = MutableStateFlow(listOf())
    val gif: StateFlow<List<GifImage>> = _gif.asStateFlow()

    init {
        viewModelScope.launch {
            val listGifs = getGifs.getGifFromDb()
            _gif.value = listGifs
        }

    }


}