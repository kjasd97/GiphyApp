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
    private val addGif = AddGifImagesToDbUseCase(repository)
    private val deleteGif = DeleteGifImagesFromDbUseCase(repository)
    private val getGif = GetGifFromDbUseCase(repository)


    private val _gif: MutableStateFlow<GifImage?> = MutableStateFlow(null)
    val gif: StateFlow<GifImage?> = _gif.asStateFlow()


//    suspend fun getFavouriteGifs() {
//        _gif.value = getGifs.getGifFromDb()
//    }



    suspend fun getFavouriteGif(url: String) {
        _gif.value = getGif.getGifFromDb(url)
    }

    fun insertGif(gif: GifImage) {
        viewModelScope.launch {
            addGif.addGifToDb(gif)
            _gif.value = gif
        }
    }

    fun removeGif(url: String) {
        viewModelScope.launch {
            deleteGif.deleteGifFromDb(url)
            _gif.value = null
        }
    }

}