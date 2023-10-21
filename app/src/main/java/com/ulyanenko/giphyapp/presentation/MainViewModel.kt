package com.ulyanenko.giphyapp.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulyanenko.giphyapp.data.repositoryImpl.GifImageRepositoryImpl
import com.ulyanenko.giphyapp.domain.DeleteGifUseCase
import com.ulyanenko.giphyapp.domain.GetGifImagesBySearchUseCase
import com.ulyanenko.giphyapp.domain.GetGifImagesUseCase
import com.ulyanenko.giphyapp.domain.GifImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGifImages: GetGifImagesUseCase,
    private val getGifImagesBySearch: GetGifImagesBySearchUseCase,
    private val deleteGifImage: DeleteGifUseCase
) : ViewModel() {


    private val _gifs: MutableStateFlow<List<GifImage>> = MutableStateFlow(listOf())
    val gifs: StateFlow<List<GifImage>> = _gifs.asStateFlow()

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()


    init {
        try {
            loadGifs()
        } catch (e: Exception) {
            Log.d("loadCategoriesFromDataBase", e.message.toString())
        }
    }


    fun loadGifs() {

        val loading = _loading.value
        if (loading) {
            return
        }
        _loading.value = true

        viewModelScope.launch {
            try {
                val response = getGifImages.getGifImages()
                _gifs.value = response
                _loading.value = false
            } catch (e: Exception) {
                _gifs.value = emptyList()
            }
        }
    }

    fun loadGifsBySearch(search: String?) {

        if (search == null) return

        val loading = _loading.value
        if (loading) {
            return
        }
        _loading.value = true

        viewModelScope.launch {
            try {
                val response = getGifImagesBySearch.getGifImagesBySearch(search)
                _gifs.value = response
                _loading.value = false
            } catch (e: Exception) {
                _gifs.value = emptyList()
            }
        }

    }

    fun deleteGifImage(deletedGifImage: GifImage) {
        viewModelScope.launch {
            deleteGifImage.deleteGif(deletedGifImage)
            val oldList = gifs.value.toMutableList()
            oldList.forEachIndexed { index, gifImage ->
                if (gifImage.url == deletedGifImage.url) {
                    oldList[index] = deletedGifImage.copy(deleted = true)
                }
            }
            _gifs.value = oldList
        }
    }
}