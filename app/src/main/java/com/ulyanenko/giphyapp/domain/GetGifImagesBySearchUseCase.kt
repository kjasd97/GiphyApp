package com.ulyanenko.giphyapp.domain

import javax.inject.Inject

class GetGifImagesBySearchUseCase @Inject constructor (private val gifImageRepository: GifImageRepository) {

    suspend fun getGifImagesBySearch(search: String):List<GifImage>{
        return gifImageRepository.loadImagesBySearch(search)
    }

}