package com.ulyanenko.giphyapp.domain

import android.icu.text.StringSearch

class GetGifImagesBySearchUseCase(private val gifImageRepository: GifImageRepository) {

    suspend fun getGifImagesBySearch(search: String):List<GifImage>{
        return gifImageRepository.loadImagesBySearch(search)
    }

}